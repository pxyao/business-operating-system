package per.bos.service.Impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.bos.dao.IDecidedzoneDao;
import per.bos.dao.ISubareaDao;
import per.bos.domain.Decidedzone;
import per.bos.domain.Subarea;
import per.bos.service.IDecidedzoneService;
import per.bos.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService{
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	
	@Autowired
	private ISubareaDao subareaDao;
	
	public void save(Decidedzone model,String[] subareaid) {
		decidedzoneDao.save(model);
		for (String id : subareaid) {
			Subarea subarea = subareaDao.findById(id);
			subarea.setDecidedzone(model);
		}
	}

	public void PageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

}
