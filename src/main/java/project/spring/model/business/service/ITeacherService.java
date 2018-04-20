package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.TeacherDto;

public interface ITeacherService {

	List<TeacherDto> getAllTeachers();

	TeacherDto getTeacherById(Long id);

	void saveTeacher(TeacherDto teacher);

	void updateTeacher(Long id, TeacherDto teacher);

	void deleteTeacher(Long id);

}
