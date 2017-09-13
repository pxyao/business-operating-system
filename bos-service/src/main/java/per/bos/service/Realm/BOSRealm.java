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
	
	//��֤����
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("�Զ����Realm����֤����ִ����.......");
		UsernamePasswordToken passwordToken = (UsernamePasswordToken)token;
		
		//���ҳ��������û���
		String username = passwordToken.getUsername();
		User user = userDao.finduserByUsername(username);
		if(user == null){
			//ҳ��������û���������
			return null;
		}
		//����֤��Ϣ����
		AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}
	//��Ȩ����
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//TODO �����޸�Ϊ�������ݿ�����ȡ��Ӧ��Ȩ��
		
		//Ϊ�û���Ȩ
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission("staff-list");
		return info;
	}
}
