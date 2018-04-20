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
import project.spring.model.business.service.StudentService;
import project.spring.model.dal.dto.StudentDto;

@RestController
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public List<StudentDto> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{id}")
	public StudentDto getStudentById(@RequestParam Long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping("/students")
	public void saveStudent(@RequestBody StudentDto studentToSave) {
		studentService.saveStudent(studentToSave);
	}

	@PutMapping("/students/{id}")
	public void updateStudent(@RequestParam Long id, @RequestBody StudentDto studentToUpdate) {
		studentService.updateStudent(id, studentToUpdate);
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@RequestParam Long id) {
		studentService.deleteStudent(id);
	}

}
