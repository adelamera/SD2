package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Attendence;
import project.spring.model.business.model.Lab;
import project.spring.model.business.model.Student;
import project.spring.model.dal.dto.AttendenceDto;
import project.spring.model.dal.repository.IAttendenceRepository;
import project.spring.model.dal.repository.ILabRepository;
import project.spring.model.dal.repository.IStudentRepository;

@Service
public class AttendenceService implements IAttendenceService {

	private final IAttendenceRepository attendenceRepository;
	private final IStudentRepository studentRepository;
	private final ILabRepository labRepository;

	@Autowired
	public AttendenceService(IAttendenceRepository attendenceRepository, IStudentRepository studentRepository,
			ILabRepository labRepository) {
		this.attendenceRepository = attendenceRepository;
		this.studentRepository = studentRepository;
		this.labRepository = labRepository;
	}

	@Override
	public List<AttendenceDto> getAllAttendences() {
		List<AttendenceDto> attendencesDto = new ArrayList<AttendenceDto>();
		List<Attendence> attendences = new ArrayList<Attendence>();
		attendenceRepository.findAll().forEach(attendences::add);
		attendencesDto = attendences.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return attendencesDto;
	}

	@Override
	public AttendenceDto getAttendenceById(Long id) {
		Attendence attendence = attendenceRepository.findOne(id);
		if (attendence != null) {
			return mapDto(attendence);
		} else {
			return null;
		}

	}

	@Override
	public boolean saveAttendence(AttendenceDto attendence, Long studentId, Long laboratoryId) {
		Attendence attendenceToSave = this.map(attendence);
		Student student = studentRepository.findOne(studentId);
		Lab lab = labRepository.findOne(laboratoryId);
		if ((lab != null) && (student != null)) {
			attendenceToSave.setStudent(student);
			attendenceToSave.setLaboratory(lab);
			attendenceRepository.save(attendenceToSave);
			return true;
		}
		return false;

	}

	@Override
	public boolean updateAttendence(Long id, AttendenceDto attendence) {
		Attendence attendenceToUpdate = attendenceRepository.findOne(id);
		if (attendenceToUpdate != null) {
			attendenceToUpdate = map(attendence);
			attendenceToUpdate.setAttendenceId(id);
			attendenceRepository.save(attendenceToUpdate);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteAttendence(Long id) {
		Attendence attendence = attendenceRepository.findOne(id);
		if (attendence != null) {
			attendenceRepository.delete(id);
			return true;
		}
		return false;

	}

	public AttendenceDto mapDto(Attendence attendence) {
		AttendenceDto attendenceDto = new AttendenceDto();
		return attendenceDto;
	}

	public Attendence map(AttendenceDto attendence) {
		Attendence att = new Attendence();
		return att;
	}

}
