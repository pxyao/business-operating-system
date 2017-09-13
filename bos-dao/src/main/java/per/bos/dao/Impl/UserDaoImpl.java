package per.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import per.bos.dao.IUserDao;
import per.bos.domain.User;

@Repository
public class UserDaoImpl  extends BaseDaoImpl<User> implements IUserDao{

	@SuppressWarnings("unchecked")
	public User findByUsernameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public User finduserByUsername(String username) {
		String hql = "FROM User u WHERE u.username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
