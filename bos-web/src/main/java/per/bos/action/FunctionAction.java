package per.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import per.bos.domain.Function;
import per.bos.service.IFunctionService;
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	@Autowired
	private IFunctionService functionService;
	public String listajax() throws Exception {
		List<Function> list = functionService.finAll();
		javaToJson(list, new String[]{"parentFunction"});
		return "none";
	}
	
	public String add() throws Exception {
		functionService.save(model);
		return "list";
	}
	
	public String pageQuery() throws Exception {
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pageBean);
		javaToJson(pageBean, new String[]{"parentFunction","roles","children"});
		return "none";
	}
}