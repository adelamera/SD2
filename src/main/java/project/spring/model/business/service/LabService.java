package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.apimodel.LabAPI;
import project.spring.model.dal.model.Lab;
import project.spring.model.dal.repository.ILabRepository;

@Service
public class LabService implements ILabService {

	private final ILabRepository labRepository;

	@Autowired
	public LabService(ILabRepository labRepository) {
		this.labRepository = labRepository;
	}

	@Override
	public List<LabAPI> getAllLabs() {
		List<LabAPI> labsDto = new ArrayList<LabAPI>();
		List<Lab> labs = new ArrayList<Lab>();
		labRepository.findAll().forEach(labs::add);
		labsDto = labs.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return labsDto;
	}

	@Override
	public LabAPI getLabById(Long id) {
		Lab lab = labRepository.findOne(id);
		if (lab != null) {
			return mapDto(lab);
		} else {
			return null;
		}

	}

	@Override
	public boolean saveLab(LabAPI lab) {
		Lab labToSave = this.map(lab);
		if (((labToSave.getLaboratoryNr() > 14) || (labToSave.getLaboratoryNr() < 1))
				|| (labRepository.findByLaboratoryNr(labToSave.getLaboratoryNr()) != null)) {
			return false;
		} else {
			labRepository.save(labToSave);
			return true;
		}

	}

	@Override
	public boolean updateLab(Long id, LabAPI lab) {
		Lab labToUpdate = labRepository.findOne(id);
		if (labToUpdate != null) {
			if ((labToUpdate.getLaboratoryNr() <= 14) && (labToUpdate.getLaboratoryNr() >= 1)) {
				if (labRepository.findByLaboratoryNr(labToUpdate.getLaboratoryNr()).getLaboratoryId() == id) {
					labToUpdate = map(lab);
					labToUpdate.setLaboratoryId(id);
					labRepository.save(labToUpdate);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteLab(Long id) {
		Lab labToDelete = labRepository.findOne(id);
		if (labToDelete != null) {
			labRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<LabAPI> getAllLabsByKeyword(String keyword) {
		List<LabAPI> labsDto = new ArrayList<LabAPI>();
		Set<Lab> labs = new HashSet<Lab>();
		labRepository.findAllByCurriculaContaining(keyword).forEach(labs::add);
		labRepository.findAllByDescriptionContaining(keyword).forEach(labs::add);
		labsDto = labs.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return labsDto;
	}

	public LabAPI mapDto(Lab lab) {
		LabAPI labDto = new LabAPI(lab.getLaboratoryNr(), lab.getDate(), lab.getTitle(), lab.getCurricula(),
				lab.getDescription());
		return labDto;
	}

	public Lab map(LabAPI lab) {
		Lab laboratory = new Lab(lab.getLaboratoryNr(), lab.getDate(), lab.getTitle(), lab.getCurricula(),
				lab.getDescription());
		return laboratory;
	}

}
