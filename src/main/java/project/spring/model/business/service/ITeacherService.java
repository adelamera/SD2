package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.TeacherDto;

public interface ITeacherService {

	List<TeacherDto> getAllTeachers();

	TeacherDto getTeacherById(Long id);

	boolean saveTeacher(TeacherDto teacher);

	boolean updateTeacher(Long id, TeacherDto teacher);

	boolean deleteTeacher(Long id);

	TeacherDto login(String username, String password);

}
