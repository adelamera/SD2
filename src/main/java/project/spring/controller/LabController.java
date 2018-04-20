package project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.model.business.service.LabService;
import project.spring.model.dal.dto.LabDto;

@RestController
public class LabController {

	private final LabService labService;

	@Autowired
	public LabController(LabService labService) {
		this.labService = labService;
	}

	@GetMapping("/labs")
	public List<LabDto> getAllLabs() {
		return labService.getAllLabs();
	}

	@GetMapping("/labs/{id}")
	public LabDto getLabById(@RequestParam Long id) {
		return labService.getLabById(id);
	}

	@PostMapping("/labs")
	public void saveLab(@RequestBody LabDto labToSave) {
		labService.saveLab(labToSave);
	}

	@PutMapping("/labs/{id}")
	public void updateLab(@RequestParam Long id, @RequestBody LabDto labToUpdate) {
		labService.updateLab(id, labToUpdate);
	}

	@DeleteMapping("/labs/{id}")
	public void deleteLab(@RequestParam Long id) {
		labService.deleteLab(id);
	}

}
