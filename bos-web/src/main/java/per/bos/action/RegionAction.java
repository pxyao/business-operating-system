package per.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import per.bos.domain.Region;
import per.bos.service.IRegionService;
import per.bos.utils.PageBean;
import per.bos.utils.PinYin4jUtils;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	
	@Autowired
	private IRegionService regionService;
	
	private File regionFile;
	
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}

	/**
	 * 区域导入
	 */
	public String importXls() throws Exception {
		//使用poi解析excel文件
		List<Region> list = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		//根据名称获取指定的sheet
		HSSFSheet hssfSheet = workbook.getSheet("Sheet1");
		for (Row row : hssfSheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			
			province = province.substring(0,province.length()-1);
			
			city = city.substring(0,city.length()-1);
			
			district = district.substring(0,district.length()-1);
		
			String info = province+city+district;
			
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			
			String headString = StringUtils.join(headByString);
			
			String shortcode = PinYin4jUtils.hanziToPinyin(headString);
			
			String citycode = PinYin4jUtils.hanziToPinyin(city,"");
			
			region.setCitycode(citycode);
			
			region.setShortcode(shortcode);
			
			list.add(region);
		}
		regionService.importXls(list);
		return "none";
	}
	
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String pageQuery() throws Exception {
		regionService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[]{"subareas","currentPage","detachedCriteria","pageSize"});
		return "none";
	}
	
	//页面传过来的条件
	private String q;
	
	public void setQ(String q) {
		this.q = q;
	}
	
	public String getQ() {
		return q;
	}

	/**
	 * 查询所有区域，写回json数据
	 * @return
	 * @throws Exception
	 */
	public String listajax() throws Exception {
		List<Region> list;
		if (StringUtils.isNotBlank(q)) {
			list = regionService.findRegionByQ(q);
		}else {
			list = regionService.findAll();
		}
		
		this.javaToJson(list, new String[]{"subareas"});
		return "none";
	}
}
