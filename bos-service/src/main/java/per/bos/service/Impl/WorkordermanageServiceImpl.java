package per.bos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import per.bos.dao.IWorkordermanageDao;
import per.bos.domain.Workordermanage;
import per.bos.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService{
	
	@Autowired
	private IWorkordermanageDao workordermanageDao;

	public void save(Workordermanage model) {
		workordermanageDao.save(model);
	}

	public List<Workordermanage> findAll() {
		List<Workordermanage> list = workordermanageDao.findAll();
		return list;
	}
}
