package per.bos.service.Realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import per.bos.dao.IUserDao;
import per.bos.domain.User;

public class BOSRealm extends AuthorizingRealm{
	
	@Autowired
	private IUserDao userDao;
	
	//认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("自定义的Realm中认证方法执行了.......");
		UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
		
		//获得页面输入的用户名
		String username = passwordToken.getUsername();
		User user = userDao.finduserByUsername(username);
		if(user == null){
			//页面输入的用户名不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}
	//授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//TODO 后期修改为根据数据库来获取相应的权限
		
		//为用户授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission("staff-list");
		return info;
	}
}
