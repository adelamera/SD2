package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.apimodel.StudentAPI;
import project.spring.model.dal.dbmodel.Student;
import project.spring.model.dal.dbmodel.User;
import project.spring.model.dal.repository.IStudentRepository;
import project.spring.model.dal.repository.IUserRepository;
import project.spring.utils.EmailValidator;
import project.spring.utils.PasswordEncryptor;
import project.spring.utils.SecureTokenGenerator;

@Service
public class StudentService implements IStudentService {

	private final IStudentRepository studentRepository;
	private final IUserRepository userRepository;

	@Autowired
	public StudentService(IStudentRepository studentRepository, IUserRepository userRepository) {
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<StudentAPI> getAllStudents() {
		List<StudentAPI> studentsDto = new ArrayList<StudentAPI>();
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(students::add);
		studentsDto = students.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return studentsDto;
	}

	@Override
	public StudentAPI getStudentById(Long id) {
		Student student = studentRepository.findOne(id);
		if (student != null) {
			return mapDto(student);
		} else {
			return null;
		}
	}

	@Override
	public String saveStudent(StudentAPI student) {
		Student studentToSave = this.map(student);
		if (student.getEmail() != null) {
			if (studentRepository.findByEmail(studentToSave.getEmail()) != null) {
				return null;
			} else {
				if (EmailValidator.validate(student.getEmail())) {
					studentToSave.setUsername(SecureTokenGenerator.nextToken());
					studentRepository.save(studentToSave);
					return studentToSave.getUsername();
				}

			}
		}
		return null;
	}

	@Override
	public boolean updateStudent(Long id, StudentAPI student) {
		Student studentToUpdate = studentRepository.findOne(id);
		if (studentToUpdate != null) {
			if (EmailValidator.validate(student.getEmail())) {
				User userToUpdate = userRepository.findByUsername(studentToUpdate.getUsername());
				userToUpdate.setUsername(student.getUsername());
				userToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(student.getPassword()));
				studentToUpdate = map(student);
				studentToUpdate.setStudentId(id);
				studentToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(studentToUpdate.getPassword()));
				studentRepository.save(studentToUpdate);
				userRepository.save(userToUpdate);
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean deleteStudent(Long id) {
		Student studentToDelete = studentRepository.findOne(id);
		if (studentToDelete != null) {
			User user = userRepository.findByUsername(studentToDelete.getUsername());
			studentRepository.delete(id);
			userRepository.delete(user.getUserId());
			return true;
		}
		return false;

	}

	@Override
	public boolean register(String username, StudentAPI student) {
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
				User user = new User(studentToUpdate.getUsername(), studentToUpdate.getPassword(), "student");
				userRepository.save(user);
				return true;
			}
		}
		return false;

	}

	@Override
	public StudentAPI login(String username, String password) {
		Student student = studentRepository.findByUsername(username);
		if (student != null) {
			if (student.getPassword().equals(PasswordEncryptor.setPasswordEncrypt(password))) {
				return mapDto(student);
			}
		}
		return null;
	}

	public StudentAPI mapDto(Student student) {
		StudentAPI studentDto = new StudentAPI(student.getName(), student.getEmail(), student.getUsername(),
				student.getPassword(), student.getGroup(), student.getHobby());
		return studentDto;
	}

	public Student map(StudentAPI student) {
		Student stud = new Student(student.getName(), student.getEmail(), student.getUsername(), student.getPassword(),
				student.getGroup(), student.getHobby());
		return stud;
	}

	@Override
	public Long getId(String username, String password) {
		Student student = studentRepository.findByUsernameAndPassword(username, PasswordEncryptor.setPasswordEncrypt(password));
		if (student != null) {
			return student.getStudentId();
		}
		return Long.valueOf(0);
	}

}
