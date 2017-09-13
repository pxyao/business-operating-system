package per.bos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.bos.dao.IUserDao;
import per.bos.domain.User;
import per.bos.service.IUserService;
import per.bos.utils.MD5Utils;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userdao;
	
	public User login(User model) {
		String password = MD5Utils.md5(model.getPassword());
		User user = userdao.findByUsernameAndPassword(model.getUsername(),password);
		return user;
	}
	
	/**
	 * 根据用户id修改密码
	 */
	public void editPassWord(String id, String password) {
		password = MD5Utils.md5(password);
		userdao.excuteUpdate("user.editpassword", password,id);
	}
}
