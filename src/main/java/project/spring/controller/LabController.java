package project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.model.business.apimodel.LabAPI;
import project.spring.model.business.service.LabService;

@RestController
public class LabController {

	private final LabService labService;

	@Autowired
	public LabController(LabService labService) {
		this.labService = labService;
	}

	@GetMapping("/labs")
	public List<LabAPI> getAllLabs() {
		return labService.getAllLabs();
	}

	@GetMapping("/labs/{keyword}")
	public List<LabAPI> getAllLabsByKeyword(@RequestParam String keyword) {
		return labService.getAllLabsByKeyword(keyword);
	}

	@GetMapping("/labs/{id}")
	public ResponseEntity<LabAPI> getLabById(@RequestParam Long id) {
		LabAPI lab = labService.getLabById(id);
		if (lab == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(lab);
		}
	}

	@PostMapping("/labs")
	public ResponseEntity<String> saveLab(@RequestBody LabAPI labToSave) {
		boolean saved = labService.saveLab(labToSave);
		if (saved == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The lab number is invalid");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The lab was successfully saved");
		}
	}

	@PutMapping("/labs/{id}")
	public ResponseEntity<String> updateLab(@RequestParam Long id, @RequestBody LabAPI labToUpdate) {
		boolean updated = labService.updateLab(id, labToUpdate);
		if (updated == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The lab number is invalid");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The lab was successfully updated");
		}
	}

	@DeleteMapping("/labs/{id}")
	public ResponseEntity<String> deleteLab(@RequestParam Long id) {
		boolean deleted = labService.deleteLab(id);
		if (deleted == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The lab doesn't exist");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The lab was successfully deleted");
		}

	}

}
