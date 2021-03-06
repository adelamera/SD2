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

import project.spring.model.business.apimodel.TeacherAPI;
import project.spring.model.business.service.TeacherService;

@RestController
public class TeacherController {

	private final TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping("/teachers")
	public List<TeacherAPI> getAllTeachers() {
		return teacherService.getAllTeachers();
	}

	@GetMapping("/teachers/id")
	public ResponseEntity<TeacherAPI> getTeacherById(@RequestParam Long id) {
		TeacherAPI teacher = teacherService.getTeacherById(id);
		if (teacher == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(teacher);
		}
	}

	@PostMapping("/teachers")
	public ResponseEntity<String> saveTeacher(@RequestBody TeacherAPI teacherToSave) {
		boolean saved = teacherService.saveTeacher(teacherToSave);
		if (saved == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The teacher already exists");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The teacher was successfully created");
		}

	}

	@PutMapping("/teachers/id")
	public ResponseEntity<String> updateTeacher(@RequestParam Long id, @RequestBody TeacherAPI teacherToUpdate) {
		boolean updated = teacherService.updateTeacher(id, teacherToUpdate);
		if (updated) {
			return ResponseEntity.status(HttpStatus.OK).body("The teacher was updated");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The teacher doesn't exist");
		}
	}

	@DeleteMapping("/teachers/id")
	public ResponseEntity<String> deleteTeacher(@RequestParam Long id) {
		boolean deleted = teacherService.deleteTeacher(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.OK).body("The teacher was deleted");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The teacher doesn't exist");
		}
	}

	@GetMapping("/teachers/login")
	public ResponseEntity<TeacherAPI> login(@RequestParam String username, @RequestParam String password) {
		TeacherAPI teacher = teacherService.login(username, password);
		if (teacher == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(teacher);
		}
	}

}
