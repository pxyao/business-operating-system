package per.bos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import per.bos.dao.IRegionDao;
import per.bos.domain.Region;
import per.bos.service.IRegionService;
import per.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService{
	
	@Autowired
	private IRegionDao regionDao;
	
	public void importXls(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
	}

	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}

	public List<Region> findAll() {
		return regionDao.findAll();
	}

	/**
	 * ����ҳ�洫��������������ģ����ѯ
	 */
	public List<Region> findRegionByQ(String q) {
		return regionDao.findRegionByQ(q);
	}


}
