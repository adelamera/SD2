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

import project.spring.model.business.service.TeacherService;
import project.spring.model.dal.dto.TeacherDto;

@RestController
public class TeacherController {

	private final TeacherService teacherService;

	@Autowired
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@GetMapping("/teachers")
	public List<TeacherDto> getAllTeachers() {
		return teacherService.getAllTeachers();
	}

	@GetMapping("/teachers/{id}")
	public TeacherDto getTeacherById(@RequestParam Long id) {
		return teacherService.getTeacherById(id);
	}

	@PostMapping("/teachers")
	public void saveTeacher(@RequestBody TeacherDto teacherToSave) {
		teacherService.saveTeacher(teacherToSave);
	}

	@PutMapping("/teachers/{id}")
	public void updateTeacher(@RequestParam Long id, @RequestBody TeacherDto teacherToUpdate) {
		teacherService.updateTeacher(id, teacherToUpdate);
	}

	@DeleteMapping("/teachers/{id}")
	public void deleteTeacher(@RequestParam Long id) {
		teacherService.deleteTeacher(id);
	}

}
