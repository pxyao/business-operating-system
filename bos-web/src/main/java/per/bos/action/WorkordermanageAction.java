package per.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import per.bos.domain.Workordermanage;
import per.bos.service.IWorkordermanageService;
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage>{
	
	@Autowired
	private IWorkordermanageService workordermanageService;
	
	public String add() throws Exception {
		workordermanageService.save(model);
		return "list";
	}
	public String list() throws Exception {
		List<Workordermanage> list = workordermanageService.findAll();
		javaToJson(list, new String[]{""});
		return "none";
	}
}
