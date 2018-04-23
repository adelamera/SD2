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

import project.spring.model.business.apimodel.StudentAPI;
import project.spring.model.business.service.StudentService;

@RestController
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public List<StudentAPI> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentAPI> getStudentById(@RequestParam Long id) {
		StudentAPI student = studentService.getStudentById(id);
		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(student);
		}
	}

	@GetMapping("/students/login")
	public ResponseEntity<StudentAPI> login(@RequestParam String username, @RequestParam String password) {
		StudentAPI student = studentService.login(username, password);
		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(student);
		}
	}

	@PostMapping("/students")
	public ResponseEntity<String> saveStudent(@RequestBody StudentAPI studentToSave) {
		String username = studentService.saveStudent(studentToSave);
		if (username == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is not valid");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(username);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<String> updateStudent(@RequestParam Long id, @RequestBody StudentAPI studentToUpdate) {
		boolean updated = studentService.updateStudent(id, studentToUpdate);
		if (updated) {
			return ResponseEntity.status(HttpStatus.OK).body("The student was updated");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The student doesn't exist");
		}
	}

	@PutMapping("/students/{username}")
	public ResponseEntity<String> register(@RequestParam String username, @RequestBody StudentAPI studentToUpdate) {
		boolean registered = studentService.register(username, studentToUpdate);
		if (registered) {
			return ResponseEntity.status(HttpStatus.OK).body("Successful registration");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
		boolean deleted = studentService.deleteStudent(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.OK).body("The student was deleted");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The student doesn't exist");
		}
	}

}
