package per.bos.service;

import java.util.List;

import per.bos.domain.Staff;
import per.bos.utils.PageBean;

public interface IStaffService {

	public void save(Staff model);

	public void PageQuery(PageBean pageBean);

	public void deleteBatch(String ids);

	public Staff findById(String id);

	public void update(Staff staff);

	public List<Staff> findListNotDel();

}
