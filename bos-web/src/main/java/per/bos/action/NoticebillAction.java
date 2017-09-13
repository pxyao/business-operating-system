package per.bos.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import per.bos.domain.Noticebill;
import per.bos.service.INoticebillService;
import per.crm.service.Customer;
import per.crm.service.ICustomerService;

/**
 * ҵ��֪ͨ��
 * @author ����ҫ
 *
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
	//ע��crm�ͻ��˴������
	@Autowired
	private ICustomerService proxy;
	
	public void setProxy(ICustomerService proxy) {
		this.proxy = proxy;
	}
	
	/**
	 * Զ�̵���crm���񣬸����ֻ��Ų�ѯ�ͻ���Ϣ
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
	 * ����ҵ��֪ͨ�� �������Զ��ֵ�
	 */
	public String add() throws Exception {
		noticebillservice.save(model);
		return "list";
	}
}
