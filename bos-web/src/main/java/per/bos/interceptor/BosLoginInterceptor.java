package per.bos.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import per.bos.domain.User;
import per.bos.utils.BOSUtils;

/**
 * 自定义拦拦截器 用户未登录，自动跳转到登录页面
 * @author 庞湘耀
 *
 */
public class BosLoginInterceptor extends MethodFilterInterceptor{
	
	//拦截方法
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = BOSUtils.getUser();
		if(user == null){
			//没有登录 返回结果集
			return "login";
		}
		//放行
		return invocation.invoke();
	}
	
}
