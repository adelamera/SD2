package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.LabDto;

public interface ILabService {

	List<LabDto> getAllLabs();

	LabDto getLabById(Long id);

	void saveLab(LabDto Lab);

	void updateLab(Long id, LabDto Lab);

	void deleteLab(Long id);

}
