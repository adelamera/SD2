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

import project.spring.model.business.apimodel.AssignmentAPI;
import project.spring.model.business.service.AssignmentService;

@RestController
public class AssignmentController {

	private final AssignmentService assignmentService;

	@Autowired
	public AssignmentController(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	@GetMapping("/assignments")
	public List<AssignmentAPI> getAllAssignments() {
		return assignmentService.getAllAssignments();
	}

	@GetMapping("/assignments/id")
	public ResponseEntity<AssignmentAPI> getAssignmentById(@RequestParam Long id) {
		AssignmentAPI assignment = assignmentService.getAssignmentById(id);
		if (assignment == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(assignment);
		}
	}

	@PostMapping("/assignments")
	public ResponseEntity<String> saveAssignment(@RequestParam Long laboratoryId,
			@RequestBody AssignmentAPI assignmentToSave) {
		boolean saved = assignmentService.saveAssignment(assignmentToSave, laboratoryId);
		if (saved == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The laboratoryId is invalid");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The assignment was successfully saved");
		}
	}

	@PutMapping("/assignments/id")
	public ResponseEntity<String> updateAssignment(@RequestParam Long id,
			@RequestBody AssignmentAPI assignmentToUpdate) {
		boolean updated = assignmentService.updateAssignment(id, assignmentToUpdate);
		if (updated == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The assignment doesn't exist");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The assignment was successfully updated");
		}
	}

	@DeleteMapping("/assignments/id")
	public ResponseEntity<String> deleteAssignment(@RequestParam Long id) {
		boolean deleted = assignmentService.deleteAssignment(id);
		if (deleted == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The assignment doesn't exist");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The assignment was successfully deleted");
		}
	}

	@GetMapping("/assignments/laboratory/id")
	public List<AssignmentAPI> getAllAssignmentsForLab(@RequestParam Long laboratoryId) {
		return assignmentService.getAllAssignmentsForLab(laboratoryId);
	}

}
