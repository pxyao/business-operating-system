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
	 * ��ҳ��ѯ
	 */
	public String PageQuery(){
		DetachedCriteria dc = pageBean.getDc();
		//��̬��ӹ�������
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)){
			//��ӹ������������ݵ�ַ�ؼ���ģ����ѯ
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		Region region = model.getRegion();
		if(region != null){
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)){
				//��ӹ�������������ʡ��ģ����ѯ-----��������ѯ��ʹ�ñ�����ʽʵ��
				//����һ�����������й��������������������
				//����������������������
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)){
				//��ӹ���������������ģ����ѯ-----��������ѯ��ʹ�ñ�����ʽʵ��
				//����һ�����������й��������������������
				//����������������������
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				//��ӹ���������������ģ����ѯ-----��������ѯ��ʹ�ñ�����ʽʵ��
				//����һ�����������й��������������������
				//����������������������
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
		//ɾ��������
		subareaService.deleteSubarea(ids);
		return "list";
	}
	
	public String exportXls() throws Exception {
		//��ѯ��������
		List<Subarea> list = subareaService.findAll();
		
		//ʹ��POI����д��Excel�ļ���
		//���ڴ��д���һ��Excel�ļ�
		HSSFWorkbook workbook = new HSSFWorkbook();
		//����һ����ǩ
		HSSFSheet sheet = workbook.createSheet("��������");
		//����������
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("�������");
		headRow.createCell(1).setCellValue("��ʼ���");
		headRow.createCell(2).setCellValue("�������");
		headRow.createCell(3).setCellValue("�������");
		headRow.createCell(4).setCellValue("λ��");
		headRow.createCell(5).setCellValue("ʡ����");
		//���ڴ��д���һ����
		
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(4).setCellValue(subarea.getPosition());
			dataRow.createCell(5).setCellValue(subarea.getRegion().getName());
		}
		
		String filename = "��������.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		//ʹ������������ļ�����(һ���� ����ͷ)
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		
		//��ȡ�ͻ������������
		String  agent = ServletActionContext.getRequest().getHeader("User-Agent");
		
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		
		workbook.write(out);
		
		return "none";
	}
	
	/**
	 * ��ѯ������δ��������ȥ�ķ���������json
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
	 * ���ݶ���ID�����Ҷ���
	 * @return
	 * @throws Exception
	 */
	public String findListByDecidedzoneId() throws Exception {
		List<Subarea> list = subareaService.findListByDecidedzoneId(decidedzoneId);
		this.javaToJson(list, new String[]{"decidedzone","subareas"});
		return "none";
	}
}