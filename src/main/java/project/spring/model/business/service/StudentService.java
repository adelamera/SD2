package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Student;
import project.spring.model.dal.dto.StudentDto;
import project.spring.model.dal.repository.IStudentRepository;
import project.spring.utils.PasswordEncryptor;
import project.spring.utils.SecureTokenGenerator;

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
		if (student != null) {
			return mapDto(student);
		} else {
			return null;
		}
	}

	@Override
	public String saveStudent(StudentDto student) {
		Student studentToSave = this.map(student);
		if (studentRepository.findByEmail(studentToSave.getEmail()) != null) {
			return null;
		} else {
			studentToSave.setUsername(SecureTokenGenerator.nextToken());
			studentRepository.save(studentToSave);
			return studentToSave.getUsername();

		}
	}

	@Override
	public boolean updateStudent(Long id, StudentDto student) {
		Student studentToUpdate = studentRepository.findOne(id);
		if (studentToUpdate != null) {
			studentToUpdate = map(student);
			studentToUpdate.setStudentId(id);
			studentToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(studentToUpdate.getPassword()));
			studentRepository.save(studentToUpdate);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean deleteStudent(Long id) {
		Student studentToDelete = studentRepository.findOne(id);
		if (studentToDelete != null) {
			studentRepository.delete(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean register(String username, StudentDto student) {
		Student studentToUpdate = studentRepository.findByUsername(username);
		if (studentToUpdate != null) {
			Long id = studentToUpdate.getStudentId();
			String email = studentToUpdate.getEmail();
			if ((email.equals(student.getEmail()))
					&& (studentRepository.findByUsername(student.getUsername()) == null)) {
				studentToUpdate = map(student);
				studentToUpdate.setStudentId(id);
				studentToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(studentToUpdate.getPassword()));
				studentRepository.save(studentToUpdate);
				return true;
			}
		}
		return false;

	}

	@Override
	public StudentDto login(String username, String password) {
		Student student = studentRepository.findByUsername(username);
		if (student != null) {
			if (student.getPassword().equals(PasswordEncryptor.setPasswordEncrypt(password))) {
				return mapDto(student);
			}
		}
		return null;
	}

	public StudentDto mapDto(Student student) {
		StudentDto studentDto = new StudentDto(student.getName(), student.getEmail(), student.getUsername(),
				student.getPassword(), student.getGroup(), student.getHobby());
		return studentDto;
	}

	public Student map(StudentDto student) {
		Student stud = new Student(student.getName(), student.getEmail(), student.getUsername(), student.getPassword(),
				student.getGroup(), student.getHobby());
		return stud;
	}

}
