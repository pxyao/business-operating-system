package per.bos.service;

import java.util.List;

import per.bos.domain.Region;
import per.bos.utils.PageBean;

public interface IRegionService {

	public void importXls(List<Region> regionList);

	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findRegionByQ(String q);

}
