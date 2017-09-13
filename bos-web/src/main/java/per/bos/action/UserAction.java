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
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && vaildcode.equals(checkcode)){
//			//验证码正确
//			User user = userService.login(model);
//			if(user != null){
//				ServletActionContext.getContext().getSession().put("loginUser", user);
//				return "home";
//			}else{
//				addActionError("用户名或密码错误");
//				return "login";
//			}
			Subject subject = SecurityUtils.getSubject();
			String password = model.getPassword();
			password = MD5Utils.md5(password);//获得用户对象
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
			//验证码错误
			//提示错误
			this.addActionError("验证码错误");
			return "login";
		}
	}
	
	/**
	 * 用户登出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		BOSUtils.getSession().invalidate();
		return "login";
	}
	
	/**
	 * 修改当前用户密码
	 * @return
	 * @throws Exception
	 */
	public String editPassWord() throws Exception {
		//获取当前登录用户
		String f = "1";
		User user = BOSUtils.getUser();
		//AJAX请求不需要跳页面
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
