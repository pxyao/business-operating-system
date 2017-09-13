package per.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import per.bos.domain.User;

/**
 * BOS项目工具类
 * @author 庞湘耀
 *
 */
public class BOSUtils {
	//获取Session
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	//获取用户
	public static User getUser(){
		User user = (User) getSession().getAttribute("loginUser");
		return user;
	}
}
