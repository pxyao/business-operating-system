package per.bos.dao;

import per.bos.domain.User;

public interface IUserDao extends IBaseDao<User>{

	public User findByUsernameAndPassword(String username, String password);

	public User finduserByUsername(String username);

}
