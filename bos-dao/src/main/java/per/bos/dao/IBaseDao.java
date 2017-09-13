package per.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import per.bos.domain.Region;
import per.bos.utils.PageBean;

public interface IBaseDao<T> {
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	public List<T> findByCriteria(DetachedCriteria dc);
	public void excuteUpdate(String queryName,Object...objects);
	public void pageQuery(PageBean pageBean);
	public void saveOrUpdate(Region region);
}