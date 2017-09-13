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
	 * ����ҵ��֪ͨ�� ���г����Զ��ֵ�
	 */
	public void save(Noticebill noticebill) {
		User user = BOSUtils.getUser();
		noticebill.setUser(user);
		noticebillDao.save(noticebill);
		
		//��ȡȡ����ַ
		String pickaddress = noticebill.getPickaddress();
		//��ȡ����ID
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);
		if (decidedzoneId != null) {
			//�鵽id���Զ��ֵ�
			//��ȡ����
			Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
			//��ȡ�ö�����Ա��
			Staff staff = decidedzone.getStaff();
			//����ȡ��Ա
			noticebill.setStaff(staff);
			//��֧�ֵ�����Ϊ�Զ��ֵ�
			noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO);
		
			//Ϊȡ��Ա����һ������
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//׷������
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//��������ʱ�䣬��ǰϵͳʱ��
			workbill.setNoticebill(noticebill);//����ҵ��֪ͨ��
			workbill.setPickstate(Workbill.PICKSTATE_NO);//ȡ��״̬��δȡ��
			workbill.setRemark(noticebill.getRemark());//���ñ���
			workbill.setStaff(noticebill.getStaff());//����Ա��
			workbill.setType(Workbill.TYPE_1);//���ù������� �µ�
			//���湤��
			workbillDao.save(workbill);
			
			//���ö���ƽ̨ ������ 
		}else{
			//�˹��ֵ�
			noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
			
		}
	}
}
