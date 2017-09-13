package per.bos.service;

import per.bos.domain.User;

public interface IUserService {

	User login(User model);

	void editPassWord(String id, String password);

}
