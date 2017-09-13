package per.bos.utils;

import java.util.List;
/**
 * 封装分页属性
 * @author 庞湘耀
 *
 */
import org.hibernate.criterion.DetachedCriteria;
public class PageBean {
	private int currentPage;//当前页码
	private int PageSize;	//页面大小
	private int total;//总页数
	private List rows; // 当前也需要显示的数据集合
	private DetachedCriteria dc; //离线查询条件
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public DetachedCriteria getDc() {
		return dc;
	}
	public void setDc(DetachedCriteria dc) {
		this.dc = dc;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", PageSize=" + PageSize + ", total=" + total + ", rows=" + rows
				+ ", dc=" + dc + "]";
	}
}
