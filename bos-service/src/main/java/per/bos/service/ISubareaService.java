package per.bos.service;

import java.util.List;

import per.bos.domain.Subarea;
import per.bos.utils.PageBean;

public interface ISubareaService {

	void save(Subarea subarea);

	void PageQuery(PageBean pageBean);

	void deleteSubarea(String ids);

	List<Subarea> findAll();

	List<Subarea> findListNotAssociation();

	List<Subarea> findListByDecidedzoneId(String decidedzoneId);
}
