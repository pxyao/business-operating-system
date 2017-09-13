package per.bos.dao;

import java.util.List;

import per.bos.domain.Function;

public interface IFunctionDao extends IBaseDao<Function>{
	public List<Function> findHasNotParentFunction();
}
