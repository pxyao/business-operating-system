package per.bos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import per.bos.dao.IFunctionDao;
import per.bos.domain.Function;
import per.bos.service.IFunctionService;
import per.bos.utils.PageBean;

@Service
@Transactional
public class FunctionService implements IFunctionService{
	@Autowired
	private IFunctionDao functionDao;
	
	public List<Function> finAll() {
		return functionDao.findHasNotParentFunction();
	}

	/**
	 * ÃÌº”»®œﬁ
	 */
	public void save(Function model) {
		Function parentFunction = model.getParentFunction();
		if (parentFunction != null && parentFunction.getId().equals("")) {
			model.setParentFunction(null);
		}
		functionDao.save(model);
	}

	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}
}
