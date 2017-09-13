package per.bos.service.Impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import per.bos.dao.IDecidedzoneDao;
import per.bos.dao.INoticebillDao;
import per.bos.dao.IWorkbillDao;
import per.bos.domain.Decidedzone;
import per.bos.domain.Noticebill;
import per.bos.domain.Staff;
import per.bos.domain.User;
import per.bos.domain.Workbill;
import per.bos.service.INoticebillService;
import per.bos.utils.BOSUtils;
import per.crm.service.ICustomerService;

@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService{
	@Autowired
	private INoticebillDao noticebillDao;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private IWorkbillDao workbillDao;
	/**
	 * 保存业务通知单 还有尝试自动分单
	 */
	public void save(Noticebill noticebill) {
		User user = BOSUtils.getUser();
		noticebill.setUser(user);
		noticebillDao.save(noticebill);
		
		//获取取件地址
		String pickaddress = noticebill.getPickaddress();
		//获取定区ID
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);
		if (decidedzoneId != null) {
			//查到id就自动分单
			//获取定区
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			//获取该定区的员工
			Staff staff = decidedzone.getStaff();
			//设置取派员
			noticebill.setStaff(staff);
			//分支分单类型为自动分单
			noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO);
		
			//为取派员产生一个工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单创建时间，当前系统时间
			workbill.setNoticebill(noticebill);//关联业务通知单
			workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态，未取件
			workbill.setRemark(noticebill.getRemark());//设置备忘
			workbill.setStaff(noticebill.getStaff());//设置员工
			workbill.setType(Workbill.TYPE_1);//设置工单类型 新单
			//保存工单
			workbillDao.save(workbill);
			
			//调用短信平台 发短信 
		}else{
			//人工分单
			noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
			
		}
	}
}
