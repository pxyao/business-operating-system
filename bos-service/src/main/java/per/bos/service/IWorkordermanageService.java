package per.bos.service;

import java.util.List;

import per.bos.domain.Workordermanage;

public interface IWorkordermanageService {

	void save(Workordermanage model);

	public List<Workordermanage> findAll();

}
