package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Student;
import project.spring.model.dal.dto.StudentDto;
import project.spring.model.dal.repository.IStudentRepository;

@Service
public class StudentService implements IStudentService {

	private final IStudentRepository studentRepository;

	@Autowired
	public StudentService(IStudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<StudentDto> studentsDto = new ArrayList<StudentDto>();
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(students::add);
		studentsDto = students.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return studentsDto;
	}

	@Override
	public StudentDto getStudentById(Long id) {
		Student student = studentRepository.findOne(id);
		return mapDto(student);
	}

	@Override
	public void saveStudent(StudentDto student) {
		Student studentToSave = this.map(student);
		studentRepository.save(studentToSave);
	}

	@Override
	public void updateStudent(Long id, StudentDto student) {
		Student studentToUpdate = studentRepository.findOne(id);
		if (studentToUpdate != null) {
			studentToUpdate = map(student);
			studentToUpdate.setStudentId(id);
			studentRepository.save(studentToUpdate);
		}

	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.delete(id);

	}

	public StudentDto mapDto(Student student) {
		StudentDto studentDto = new StudentDto(student.getName(), student.getEmail(), student.getPassword(),
				student.getGroup(), student.getHobby());
		return studentDto;
	}

	public Student map(StudentDto student) {
		Student stud = new Student(student.getName(), student.getEmail(), student.getPassword(), student.getGroup(),
				student.getHobby());
		return stud;
	}

}
