package per.bos.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import per.bos.dao.IBaseDao;
import per.bos.domain.Region;
import per.bos.utils.PageBean;
/**
 * 持久层类实现
 * @author 庞湘耀
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{
	//clazz:子类的类型 
	private Class<T> entityClass;
	
	//在父类BaseDaoImpl的构造方法中动态获得entityClass
	public BaseDaoImpl() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得父类上申明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	@Resource
	//通过set方法来注入sessionFactory
	public void MysetSessionFactory(SessionFactory sessionFactory){
		setSessionFactory(sessionFactory);
	}
	
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public void update(T entity) {
		 this.getHibernateTemplate().update(entity);
	}

	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String hql = "FROM " + entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	/**
	 * 执行更新
	 */
	public void excuteUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i=0;
		for(Object object : objects){
			//为hql语句中的？赋值
			query.setParameter(i++, object);
		}
		query.executeUpdate();
	}

	/**
	 * 通用分页查询方法
	 */
	public void pageQuery(PageBean pageBean) {
		//已经封装好的属性
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria dc = pageBean.getDc();
		
		//查询total -- 总的数量
		dc.setProjection(Projections.rowCount()); //select count(*) from bc_staff
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		Long count = countList.get(0);
		pageBean.setTotal(count.intValue());
		//查询 rows -- 当前页需要展示的数据集合
		dc.setProjection(null);//select * from bc_staff
		
		//指定hibernate框架封装对象的方式
		dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		
		int firstResult = (currentPage-1)*pageSize;
		int maxResult = pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(dc,firstResult,maxResult);
		pageBean.setRows(rows);
	}

	public void saveOrUpdate(Region region) {
		this.getHibernateTemplate().saveOrUpdate(region);
	}

	public List<T> findByCriteria(DetachedCriteria dc) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}
}