package per.bos.dao;

import java.util.List;

import per.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region>{

	List<Region> findRegionByQ(String q);

}
