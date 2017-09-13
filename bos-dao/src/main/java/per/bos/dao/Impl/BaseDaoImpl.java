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
 * �־ò���ʵ��
 * @author ����ҫ
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{
	//clazz:��������� 
	private Class<T> entityClass;
	
	//�ڸ���BaseDaoImpl�Ĺ��췽���ж�̬���entityClass
	public BaseDaoImpl() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//��ø����������ķ�������
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	@Resource
	//ͨ��set������ע��sessionFactory
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
	 * ִ�и���
	 */
	public void excuteUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int i=0;
		for(Object object : objects){
			//Ϊhql����еģ���ֵ
			query.setParameter(i++, object);
		}
		query.executeUpdate();
	}

	/**
	 * ͨ�÷�ҳ��ѯ����
	 */
	public void pageQuery(PageBean pageBean) {
		//�Ѿ���װ�õ�����
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria dc = pageBean.getDc();
		
		//��ѯtotal -- �ܵ�����
		dc.setProjection(Projections.rowCount()); //select count(*) from bc_staff
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		Long count = countList.get(0);
		pageBean.setTotal(count.intValue());
		//��ѯ rows -- ��ǰҳ��Ҫչʾ�����ݼ���
		dc.setProjection(null);//select * from bc_staff
		
		//ָ��hibernate��ܷ�װ����ķ�ʽ
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