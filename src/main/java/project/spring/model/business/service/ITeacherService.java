package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.TeacherAPI;

public interface ITeacherService {

	List<TeacherAPI> getAllTeachers();

	TeacherAPI getTeacherById(Long id);

	boolean saveTeacher(TeacherAPI teacher);

	boolean updateTeacher(Long id, TeacherAPI teacher);

	boolean deleteTeacher(Long id);

	TeacherAPI login(String username, String password);

}
