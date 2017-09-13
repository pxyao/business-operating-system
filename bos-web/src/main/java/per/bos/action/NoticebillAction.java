package per.bos.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import per.bos.domain.Noticebill;
import per.bos.service.INoticebillService;
import per.crm.service.Customer;
import per.crm.service.ICustomerService;

/**
 * 业务通知单
 * @author 庞湘耀
 *
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
	//注入crm客户端代理对象
	@Autowired
	private ICustomerService proxy;
	
	public void setProxy(ICustomerService proxy) {
		this.proxy = proxy;
	}
	
	/**
	 * 远程调用crm服务，根据手机号查询客户信息
	 * @return
	 * @throws Exception
	 */
	public String findNoticeBillByTelephone() throws Exception {
		Customer customer = proxy.findCustomerByTelphone(model.getTelephone());
		this.javaToJson(customer,new String[]{});
		return "none";
	}
	
	@Autowired
	private INoticebillService noticebillservice;
	/**
	 * 保存业务通知单 并尝试自动分单
	 */
	public String add() throws Exception {
		noticebillservice.save(model);
		return "list";
	}
}
