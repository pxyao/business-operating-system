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
 * 表现层通用实现
 * @author 庞湘耀
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	protected PageBean pageBean = new PageBean();
	
	protected T model;
	//创建离线提交查询对象
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
		//获得baseAction上申明的泛型数组
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		Class<T> entityClass = (Class<T>) actualTypeArguments[0];
		
		dc = DetachedCriteria.forClass(entityClass);
		pageBean.setDc(dc);
		//通过反射创建对象
		try {
			model = entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将指定java对象转为json 并响应到客户端页面
	 * @param o
	 * @param excludes
	 */
	public void javaToJson(Object o,String[] excludes){
		//使用json-lib将PageBean写成json
		//JSONObject -- 将单一对象转换为json
		//JSONArray -- 将数组集合转化为json
		//指定哪些属性不需要转成json
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
	 * 将指定java对象转为json 并响应到客户端页面 重载方法 List
	 * @param o
	 * @param excludes
	 */
	public void javaToJson(List o,String[] excludes){
		//使用json-lib将PageBean写成json
		//JSONObject -- 将单一对象转换为json
		//JSONArray -- 将数组集合转化为json
		//指定哪些属性不需要转成json
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
