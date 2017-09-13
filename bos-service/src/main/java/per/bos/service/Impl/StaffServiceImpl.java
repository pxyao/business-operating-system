package per.bos.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import per.bos.dao.IStaffDao;
import per.bos.domain.Staff;
import per.bos.service.IStaffService;
import per.bos.utils.PageBean;

@Service
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class StaffServiceImpl implements IStaffService{
	@Autowired
	private IStaffDao staffDao;
	
	public void save(Staff staff) {
		staffDao.save(staff);
	}

	public void PageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}
	
	/**
	 * 批量删除取派员 
	 * 逻辑删除 把deltag设置为1
	 */
	public void deleteBatch(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] staffIds = ids.split(",");
			for(String id : staffIds){
				staffDao.excuteUpdate("staff.delete", id);
			}
		}
	}

	/**
	 * 根据ID查找取派员
	 */
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	public void update(Staff staff) {
		staffDao.update(staff);
	}

	/**
	 * 查询所有未删除的取派员
	 */
	public List<Staff> findListNotDel() {
		DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
		//添加过滤条件
		dc.add(Restrictions.eq("deltag", "0"));
		
		List<Staff> list = staffDao.findByCriteria(dc);
		return list;
	}
}
