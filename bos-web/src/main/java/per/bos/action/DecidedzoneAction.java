package per.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import per.bos.domain.Decidedzone;
import per.bos.service.IDecidedzoneService;
import per.crm.service.Customer;
import per.crm.service.ICustomerService;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{
	
	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	private String[] subareaid;
	
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	/**
	 * 添加定去 同时关联分区
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		decidedzoneService.save(model,subareaid);
		return "list";
	}
	
	public String PageQuery() throws Exception {
		decidedzoneService.PageQuery(pageBean);
		this.javaToJson(pageBean, new String[]{"decidedzones","subareas","currentPage","detachedCriteria","pageSize"});
		return "none";
	}
	
	@Autowired
	private ICustomerService proxy;

	/**
	 * 远程调用crm服务来获取未关联到定区的服务
	 * @param proxy
	 */
	public void setProxy(ICustomerService proxy) {
		this.proxy = proxy;
	}

	public String findListNotAssociation() throws Exception {
		List<Customer> list = proxy.findListNotAssociation();
		this.javaToJson(list, new String[]{});
		return "none";
	}
	
	public String findListHasAssociation() throws Exception {
		List<Customer> list = proxy.findListHasAssociation(model.getId());
		this.javaToJson(list, new String[]{});
		return "none";
	}
	
	private List<Integer> customerIds;

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	
	public List<Integer> getCustomerIds() {
		return customerIds;
	}

	public String assigncustomerstodecidedzone() throws Exception {
		proxy.assigncustomerstodecidedzone(model.getId(), customerIds);
		return "list";
	}
}
