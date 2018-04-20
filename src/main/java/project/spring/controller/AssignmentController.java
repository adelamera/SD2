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

import project.spring.model.business.service.AssignmentService;
import project.spring.model.dal.dto.AssignmentDto;

@RestController
public class AssignmentController {

	private final AssignmentService assignmentService;

	@Autowired
	public AssignmentController(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	@GetMapping("/assignments")
	public List<AssignmentDto> getAllassignments() {
		return assignmentService.getAllAssignments();
	}

	@GetMapping("/assignments/{id}")
	public AssignmentDto getassignmentById(@RequestParam Long id) {
		return assignmentService.getAssignmentById(id);
	}

	@PostMapping("/assignments")
	public void saveassignment(@RequestBody AssignmentDto assignmentToSave) {
		assignmentService.saveAssignment(assignmentToSave);
	}

	@PutMapping("/assignments/{id}")
	public void updateassignment(@RequestParam Long id, @RequestBody AssignmentDto assignmentToUpdate) {
		assignmentService.updateAssignment(id, assignmentToUpdate);
	}

	@DeleteMapping("/assignments/{id}")
	public void deleteassignment(@RequestParam Long id) {
		assignmentService.deleteAssignment(id);
	}

}
