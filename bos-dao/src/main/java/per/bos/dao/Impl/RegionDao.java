package per.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import per.bos.dao.IRegionDao;
import per.bos.domain.Region;


@Repository
public class RegionDao extends BaseDaoImpl<Region> implements IRegionDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> findRegionByQ(String q) {
		String hql = "FROM Region r WHERE r.shortcode LIKE ? OR"
				+ " r.citycode LIKE ? OR"
				+ " r.province LIKE ? OR"
				+ " r.city LIKE ? OR"
				+ " r.district LIKE ?";

		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}

}
