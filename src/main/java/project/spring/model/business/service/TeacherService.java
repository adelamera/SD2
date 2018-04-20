package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Teacher;
import project.spring.model.dal.dto.TeacherDto;
import project.spring.model.dal.repository.ITeacherRepository;

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
		return mapDto(teacher);
	}

	@Override
	public void saveTeacher(TeacherDto teacher) {
		Teacher teacherToSave = this.map(teacher);
		teacherRepository.save(teacherToSave);

	}

	@Override
	public void updateTeacher(Long id, TeacherDto teacher) {
		Teacher teacherToUpdate = teacherRepository.findOne(id);
		if (teacherToUpdate != null) {
			teacherToUpdate = map(teacher);
			teacherToUpdate.setTeacherId(id);
			teacherRepository.save(teacherToUpdate);
		}

	}

	@Override
	public void deleteTeacher(Long id) {
		teacherRepository.delete(id);

	}

	public TeacherDto mapDto(Teacher teacher) {
		TeacherDto teacherDto = new TeacherDto(teacher.getEmail(), teacher.getPassword());
		return teacherDto;
	}

	public Teacher map(TeacherDto teacher) {
		Teacher tech = new Teacher(teacher.getEmail(), teacher.getPassword());
		return tech;
	}

}
