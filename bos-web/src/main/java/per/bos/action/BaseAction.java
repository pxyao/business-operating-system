package per.bos.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import per.bos.utils.PageBean;

/**
 * ���ֲ�ͨ��ʵ��
 * @author ����ҫ
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	protected PageBean pageBean = new PageBean();
	
	protected T model;
	//���������ύ��ѯ����
	protected DetachedCriteria dc = null;
	
	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}
	
	@Override
	public T getModel() {
		return model;
	}
	
	public BaseAction() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//���baseAction�������ķ�������
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		
		dc = DetachedCriteria.forClass(entityClass);
		pageBean.setDc(dc);
		//ͨ�����䴴������
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ָ��java����תΪjson ����Ӧ���ͻ���ҳ��
	 * @param o
	 * @param excludes
	 */
	public void javaToJson(Object o,String[] excludes){
		//ʹ��json-lib��PageBeanд��json
		//JSONObject -- ����һ����ת��Ϊjson
		//JSONArray -- �����鼯��ת��Ϊjson
		//ָ����Щ���Բ���Ҫת��json
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(o,jsonConfig).toString();
		
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e)  {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ָ��java����תΪjson ����Ӧ���ͻ���ҳ�� ���ط��� List
	 * @param o
	 * @param excludes
	 */
	public void javaToJson(List o,String[] excludes){
		//ʹ��json-lib��PageBeanд��json
		//JSONObject -- ����һ����ת��Ϊjson
		//JSONArray -- �����鼯��ת��Ϊjson
		//ָ����Щ���Բ���Ҫת��json
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = JSONArray.fromObject(o,jsonConfig).toString();
		
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e)  {
			e.printStackTrace();
		}
	}
	
}
