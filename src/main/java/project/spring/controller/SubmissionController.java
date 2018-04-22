package project.spring.controller;

import java.util.ArrayList;
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

import project.spring.model.business.service.SubmissionService;
import project.spring.model.dal.dto.SubmissionDto;

@RestController
public class SubmissionController {

	private final SubmissionService submissionService;

	@Autowired
	public SubmissionController(SubmissionService submissionService) {
		this.submissionService = submissionService;
	}

	@GetMapping("/submissions")
	public List<SubmissionDto> getAllSubmissions() {
		return submissionService.findAllSubmissions();
	}

	@GetMapping("/submissions/students/{id}")
	public List<SubmissionDto> getAllSubmissionsOfStudent(@RequestParam Long studentId) {
		return submissionService.findAllSubmissionsOfStudent(studentId);
	}


	@GetMapping("/submissions/assignment/{id}")
	public List<Integer> getAllGradesOfAssignment(@RequestParam Long assignmentId) {
		List<SubmissionDto> submissions = submissionService.findAllSubmissionsOfAssignment(assignmentId);
		List<Integer> grades = new ArrayList<Integer>();
		for (int i = 0; i < submissions.size(); i++) {
			grades.add(submissions.get(i).getGrade());
		}
		return grades;
	}

	@GetMapping("/submissions/{id}")
	public ResponseEntity<SubmissionDto> getSubmissionById(@RequestParam Long id) {
		SubmissionDto submission = submissionService.findSubmissionById(id);
		if (submission == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(submission);
		}
	}
	

	@PostMapping("/submission/students/{id}/assignments/{id}")
	public ResponseEntity<String> saveSubmission(@RequestParam Long studentId, @RequestParam Long assignmentId,
			@RequestBody SubmissionDto submission) {
		boolean saved = submissionService.saveSubmission(submission, studentId, assignmentId);
		if (saved == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid submission");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The assignment was successfully submitted");
		}
	}

	@PutMapping("/submission/{id}")
	public ResponseEntity<String> gradeSubmission(@RequestParam Long submissionId,
			@RequestBody SubmissionDto submission) {
		boolean updated = submissionService.updateSubmission(submissionId, submission);
		if (updated == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The submission doesn't exist");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The submission was successfully graded");
		}
	}

	@DeleteMapping("/submission/{id}")
	public ResponseEntity<String> deleteSubmission(@RequestParam Long id) {
		boolean deleted = submissionService.deleteSubmission(id);
		if (deleted == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The submission doesn't exist");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The submission was successfully deleted");
		}
	}
}
