package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.apimodel.TeacherAPI;
import project.spring.model.dal.dbmodel.Teacher;
import project.spring.model.dal.dbmodel.User;
import project.spring.model.dal.repository.ITeacherRepository;
import project.spring.model.dal.repository.IUserRepository;
import project.spring.utils.PasswordEncryptor;

@Service
public class TeacherService implements ITeacherService {

	private final ITeacherRepository teacherRepository;
	private final IUserRepository userRepository;

	@Autowired
	public TeacherService(ITeacherRepository teacherRepository, IUserRepository userRepository) {
		this.teacherRepository = teacherRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<TeacherAPI> getAllTeachers() {
		List<TeacherAPI> teachersDto = new ArrayList<TeacherAPI>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		teacherRepository.findAll().forEach(teachers::add);
		teachersDto = teachers.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return teachersDto;
	}

	@Override
	public TeacherAPI getTeacherById(Long id) {
		Teacher teacher = teacherRepository.findOne(id);
		if (teacher != null) {
			return mapDto(teacher);
		} else {
			return null;
		}

	}

	@Override
	public boolean saveTeacher(TeacherAPI teacher) {
		Teacher teacherToSave = this.map(teacher);
		if (teacherRepository.findByUsername(teacherToSave.getUsername()) != null) {
			return false;
		} else {
			teacherToSave.setPassword(PasswordEncryptor.setPasswordEncrypt(teacherToSave.getPassword()));
			teacherRepository.save(teacherToSave);
			User user = new User(teacherToSave.getUsername(), teacherToSave.getPassword(), "teacher");
			userRepository.save(user);
			return true;
		}
	}

	@Override
	public boolean updateTeacher(Long id, TeacherAPI teacher) {
		Teacher teacherToUpdate = teacherRepository.findOne(id);
		if (teacherToUpdate != null) {
			User userToUpdate = userRepository.findByUsername(teacherToUpdate.getUsername());
			userToUpdate.setUsername(teacher.getUsername());
			userToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(teacher.getPassword()));
			teacherToUpdate = map(teacher);
			teacherToUpdate.setTeacherId(id);
			teacherToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(teacherToUpdate.getPassword()));
			teacherRepository.save(teacherToUpdate);
			userRepository.save(userToUpdate);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteTeacher(Long id) {
		Teacher teacherToDelete = teacherRepository.findOne(id);
		if (teacherToDelete != null) {
			User user = userRepository.findByUsername(teacherToDelete.getUsername());
			userRepository.delete(user.getUserId());
			teacherRepository.delete(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public TeacherAPI login(String username, String password) {
		Teacher teacher = teacherRepository.findByUsername(username);
		if (teacher != null) {
			if (teacher.getPassword().equals(PasswordEncryptor.setPasswordEncrypt(password))) {
				return mapDto(teacher);
			}
		}
		return null;
	}

	public TeacherAPI mapDto(Teacher teacher) {
		TeacherAPI teacherDto = new TeacherAPI(teacher.getUsername(), teacher.getPassword());
		return teacherDto;
	}

	public Teacher map(TeacherAPI teacher) {
		Teacher tech = new Teacher(teacher.getUsername(), teacher.getPassword());
		return tech;
	}

}
