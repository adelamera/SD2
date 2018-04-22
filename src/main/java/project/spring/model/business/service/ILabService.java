package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.LabDto;

public interface ILabService {

	List<LabDto> getAllLabs();

	LabDto getLabById(Long id);

	boolean saveLab(LabDto lab);

	boolean updateLab(Long id, LabDto Lab);

	boolean deleteLab(Long id);
	
	List<LabDto> getAllLabsByKeyword(String keyword);

}
