package project.spring.model.business.service;

import java.util.List;
import project.spring.model.dal.dto.StudentDto;

public interface IStudentService {

	List<StudentDto> getAllStudents();

	StudentDto getStudentById(Long id);

	String saveStudent(StudentDto student);

	boolean updateStudent(Long id, StudentDto student);

	boolean deleteStudent(Long id);

	boolean register(String username, StudentDto student);
	
	StudentDto login(String username, String password);

}
