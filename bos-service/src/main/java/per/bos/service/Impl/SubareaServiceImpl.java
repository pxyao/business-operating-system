package per.bos.service.Impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import per.bos.dao.ISubareaDao;
import per.bos.domain.Decidedzone;
import per.bos.domain.Subarea;
import per.bos.service.ISubareaService;
import per.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService{
	@Autowired
	private ISubareaDao subareaDao;
	public void save(Subarea subarea) {
		subareaDao.save(subarea);
	}
	public void PageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}
	public void deleteSubarea(String ids) {
		String[] id = ids.split(",");
		for (String subid : id) {
			subareaDao.delete(subareaDao.findById(subid)); 
		}
	}
	public List<Subarea> findAll() {
		List<Subarea> list = subareaDao.findAll();
		return list;
	}
	
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		//添加过滤条件  
		dc.add(Restrictions.isNull("decidedzone"));
		List<Subarea> list = subareaDao.findByCriteria(dc);
		return list;
	}
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
		DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		List<Subarea> list = subareaDao.findByCriteria(dc);
		return list;
	}
}
