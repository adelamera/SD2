package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Lab;
import project.spring.model.dal.dto.LabDto;
import project.spring.model.dal.repository.ILabRepository;

@Service
public class LabService implements ILabService {

	private final ILabRepository labRepository;

	@Autowired
	public LabService(ILabRepository labRepository) {
		this.labRepository = labRepository;
	}

	@Override
	public List<LabDto> getAllLabs() {
		List<LabDto> labsDto = new ArrayList<LabDto>();
		List<Lab> labs = new ArrayList<Lab>();
		labRepository.findAll().forEach(labs::add);
		labsDto = labs.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return labsDto;
	}

	@Override
	public LabDto getLabById(Long id) {
		Lab lab = labRepository.findOne(id);
		return mapDto(lab);
	}

	@Override
	public void saveLab(LabDto lab) {
		Lab labToSave = this.map(lab);
		labRepository.save(labToSave);

	}

	@Override
	public void updateLab(Long id, LabDto lab) {
		Lab labToUpdate = labRepository.findOne(id);
		if (labToUpdate != null) {
			labToUpdate = map(lab);
			labToUpdate.setLaboratoryId(id);
			labRepository.save(labToUpdate);
		}

	}

	@Override
	public void deleteLab(Long id) {
		labRepository.delete(id);
	}

	public LabDto mapDto(Lab lab) {
		LabDto labDto = new LabDto(lab.getLaboratoryNr(), lab.getDate(), lab.getTitle(), lab.getCurricula(),
				lab.getDescription());
		return labDto;
	}

	public Lab map(LabDto lab) {
		Lab laboratory = new Lab(lab.getLaboratoryNr(), lab.getDate(), lab.getTitle(), lab.getCurricula(),
				lab.getDescription());
		return laboratory;
	}

}
