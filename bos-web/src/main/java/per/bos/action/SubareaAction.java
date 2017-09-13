package per.bos.action;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import per.bos.domain.Region;
import per.bos.domain.Subarea;
import per.bos.service.ISubareaService;
import per.bos.utils.FileUtils;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{
	@Autowired
	private ISubareaService subareaService;
	public String add() throws Exception {
		subareaService.save(model);
		return "list";
	}
	/**
	 * 分页查询
	 */
	public String PageQuery(){
		DetachedCriteria dc = pageBean.getDc();
		//动态添加过滤条件
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)){
			//添加过滤条件，根据地址关键字模糊查询
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		Region region = model.getRegion();
		if(region != null){
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)){
				//添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)){
				//添加过滤条件，根据市模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				//添加过滤条件，根据区模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		subareaService.PageQuery(pageBean);
		this.javaToJson(pageBean, new String[]{"currentPage","detachedCriteria","pageSize",
						"decidedzone","subareas"});
		return "none";
	}
	
	
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String deleteBatch() throws Exception {
		//删除数据项
		subareaService.deleteSubarea(ids);
		return "list";
	}
	
	public String exportXls() throws Exception {
		//查询所有数据
		List<Subarea> list = subareaService.findAll();
		
		//使用POI将数写到Excel文件中
		//在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("分区编号");
		headRow.createCell(4).setCellValue("位置");
		headRow.createCell(5).setCellValue("省市区");
		//在内存中创建一个表
		
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(4).setCellValue(subarea.getPosition());
			dataRow.createCell(5).setCellValue(subarea.getRegion().getName());
		}
		
		String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		//使用输出流进行文件下载(一个流 两个头)
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		
		//获取客户端浏览器类型
		String  agent = ServletActionContext.getRequest().getHeader("User-Agent");
		
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		
		workbook.write(out);
		
		return "none";
	}
	
	/**
	 * 查询到所有未关联到定去的分区，返回json
	 * @return
	 * @throws Exception
	 */
	public String listajax() throws Exception {
		List<Subarea> list = subareaService.findListNotAssociation();
		
		this.javaToJson(list, new String[]{"region","decidedzone"});
		
		return "none";
	}
	
	private String decidedzoneId;
	
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}
	/**
	 * 根据定区ID来查找定区
	 * @return
	 * @throws Exception
	 */
	public String findListByDecidedzoneId() throws Exception {
		List<Subarea> list = subareaService.findListByDecidedzoneId(decidedzoneId);
		this.javaToJson(list, new String[]{"decidedzone","subareas"});
		return "none";
	}
}