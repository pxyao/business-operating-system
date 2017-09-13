package per.bos.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import per.bos.domain.Staff;
import per.bos.service.IStaffService;
/**
 * 取派员管理
 * @author 庞湘耀
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	@Autowired
	private IStaffService staffService;
	public String addStaff() throws Exception {
		staffService.save(model);
		return "list";
	}
	
	public String PageQuery() throws Exception {
		staffService.PageQuery(pageBean);
		this.javaToJson(pageBean, new String[]{"decidedzones","currentPage","detachedCriteria","pageSize"});
		return "none";
	}
	
	public String editStaff() throws Exception {
		Staff staff = staffService.findById(model.getId());
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		
		staffService.update(staff);
		return "list";
	}
	
	private String ids;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	@RequiresPermissions("staff-delete")
	public String deleteBatch() throws Exception {
		staffService.deleteBatch(ids);
		return "list";
	}
	
	public String listajax() throws Exception {
		List<Staff> list = staffService.findListNotDel();
		this.javaToJson(list, new String[]{"decidedzones"});
		return "none";
	}
}
