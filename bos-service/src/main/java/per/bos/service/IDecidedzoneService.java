package per.bos.service;

import per.bos.domain.Decidedzone;
import per.bos.utils.PageBean;

public interface IDecidedzoneService {

	void save(Decidedzone mode,String[] subareaid);

	void PageQuery(PageBean pageBean);

}
