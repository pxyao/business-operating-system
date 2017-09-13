package per.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import per.bos.dao.IFunctionDao;
import per.bos.domain.Function;

@Repository
public class FuntcionDao extends BaseDaoImpl<Function> implements IFunctionDao{
	public List<Function> findHasNotParentFunction() {
		String hql = "FROM Function f where f.parentFunction is NULL";
		return (List<Function>) this.getHibernateTemplate().find(hql);
	}
}
