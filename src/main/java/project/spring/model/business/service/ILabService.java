package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.LabAPI;

public interface ILabService {

	List<LabAPI> getAllLabs();

	LabAPI getLabById(Long id);

	boolean saveLab(LabAPI lab);

	boolean updateLab(Long id, LabAPI Lab);

	boolean deleteLab(Long id);
	
	List<LabAPI> getAllLabsByKeyword(String keyword);

}
