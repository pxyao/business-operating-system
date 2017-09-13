package per.bos.utils;

import java.util.List;
/**
 * ��װ��ҳ����
 * @author ����ҫ
 *
 */
import org.hibernate.criterion.DetachedCriteria;
public class PageBean {
	private int currentPage;//��ǰҳ��
	private int PageSize;	//ҳ���С
	private int total;//��ҳ��
	private List rows; // ��ǰҲ��Ҫ��ʾ�����ݼ���
	private DetachedCriteria dc; //���߲�ѯ����
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
