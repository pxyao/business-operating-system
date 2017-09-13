package per.bos.action;



import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import per.bos.domain.User;
import per.bos.service.IUserService;
import per.bos.utils.BOSUtils;
import per.bos.utils.MD5Utils;
import per.crm.service.ICustomerService;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	@Autowired
	private ICustomerService proxy;
	
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Autowired
	private IUserService userService;
	
	public String login() throws Exception {
		String vaildcode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//У����֤���Ƿ�������ȷ
		if(StringUtils.isNotBlank(checkcode) && vaildcode.equals(checkcode)){
//			//��֤����ȷ
//			User user = userService.login(model);
//			if(user != null){
//				ServletActionContext.getContext().getSession().put("loginUser", user);
//				return "home";
//			}else{
//				addActionError("�û������������");
//				return "login";
//			}
			Subject subject = SecurityUtils.getSubject();
			String password = model.getPassword();
			password = MD5Utils.md5(password);//����û�����
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),password);
			try {
				subject.login(token);
			} catch (Exception e) {
				e.printStackTrace();
				return "login";
			}
			User user = (User) subject.getPrincipal();
			ServletActionContext.getContext().getSession().put("loginUser", user);
			return "home";
		}else {
			//��֤�����
			//��ʾ����
			this.addActionError("��֤�����");
			return "login";
		}
	}
	
	/**
	 * �û��ǳ�
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		BOSUtils.getSession().invalidate();
		return "login";
	}
	
	/**
	 * �޸ĵ�ǰ�û�����
	 * @return
	 * @throws Exception
	 */
	public String editPassWord() throws Exception {
		//��ȡ��ǰ��¼�û�
		String f = "1";
		User user = BOSUtils.getUser();
		//AJAX������Ҫ��ҳ��
		try {
			userService.editPassWord(user.getId(),model.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			f = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(f);;
		return "none";
	}
}
