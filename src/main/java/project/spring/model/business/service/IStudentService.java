package project.spring.model.business.service;

import java.util.List;
import project.spring.model.dal.dto.StudentDto;

public interface IStudentService {

	List<StudentDto> getAllStudents();

	StudentDto getStudentById(Long id);

	void saveStudent(StudentDto student);

	void updateStudent(Long id, StudentDto student);

	void deleteStudent(Long id);

}
