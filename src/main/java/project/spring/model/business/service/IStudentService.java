package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.StudentAPI;

public interface IStudentService {

	List<StudentAPI> getAllStudents();

	StudentAPI getStudentById(Long id);

	String saveStudent(StudentAPI student);

	boolean updateStudent(Long id, StudentAPI student);

	boolean deleteStudent(Long id);

	boolean register(String username, StudentAPI student);
	
	StudentAPI login(String username, String password);
	
	Long getId(String username, String password);

}
