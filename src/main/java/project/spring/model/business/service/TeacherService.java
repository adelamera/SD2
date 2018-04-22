package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Teacher;
import project.spring.model.dal.dto.TeacherDto;
import project.spring.model.dal.repository.ITeacherRepository;
import project.spring.utils.PasswordEncryptor;

@Service
public class TeacherService implements ITeacherService {

	private final ITeacherRepository teacherRepository;

	@Autowired
	public TeacherService(ITeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<TeacherDto> getAllTeachers() {
		List<TeacherDto> teachersDto = new ArrayList<TeacherDto>();
		List<Teacher> teachers = new ArrayList<Teacher>();
		teacherRepository.findAll().forEach(teachers::add);
		teachersDto = teachers.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return teachersDto;
	}

	@Override
	public TeacherDto getTeacherById(Long id) {
		Teacher teacher = teacherRepository.findOne(id);
		if (teacher != null) {
			return mapDto(teacher);
		} else {
			return null;
		}

	}

	@Override
	public boolean saveTeacher(TeacherDto teacher) {
		Teacher teacherToSave = this.map(teacher);
		if (teacherRepository.findByUsername(teacherToSave.getUsername()) != null) {
			return false;
		} else {
			teacherToSave.setPassword(PasswordEncryptor.setPasswordEncrypt(teacherToSave.getPassword()));
			teacherRepository.save(teacherToSave);
			return true;
		}
	}

	@Override
	public boolean updateTeacher(Long id, TeacherDto teacher) {
		Teacher teacherToUpdate = teacherRepository.findOne(id);
		if (teacherToUpdate != null) {
			teacherToUpdate = map(teacher);
			teacherToUpdate.setTeacherId(id);
			teacherToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(teacherToUpdate.getPassword()));
			teacherRepository.save(teacherToUpdate);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteTeacher(Long id) {
		Teacher teacherToDelete = teacherRepository.findOne(id);
		if (teacherToDelete != null) {
			teacherRepository.delete(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public TeacherDto login(String username, String password) {
		Teacher teacher = teacherRepository.findByUsername(username);
		if (teacher != null) {
			if (teacher.getPassword().equals(PasswordEncryptor.setPasswordEncrypt(password))) {
				return mapDto(teacher);
			}
		}
		return null;
	}

	public TeacherDto mapDto(Teacher teacher) {
		TeacherDto teacherDto = new TeacherDto(teacher.getUsername(), teacher.getPassword());
		return teacherDto;
	}

	public Teacher map(TeacherDto teacher) {
		Teacher tech = new Teacher(teacher.getUsername(), teacher.getPassword());
		return tech;
	}

}
