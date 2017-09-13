package per.bos.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import per.bos.domain.User;

/**
 * BOS��Ŀ������
 * @author ����ҫ
 *
 */
public class BOSUtils {
	//��ȡSession
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	//��ȡ�û�
	public static User getUser(){
		User user = (User) getSession().getAttribute("loginUser");
		return user;
	}
}
