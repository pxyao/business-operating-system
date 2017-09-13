package per.bos.service;

import java.util.List;

import per.bos.domain.Function;
import per.bos.utils.PageBean;

public interface IFunctionService {

	public List<Function> finAll();

	public void save(Function model);

	public void pageQuery(PageBean pageBean);

}
