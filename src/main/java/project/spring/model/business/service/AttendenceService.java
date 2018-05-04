package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.apimodel.AttendenceAPI;
import project.spring.model.dal.dbmodel.Attendence;
import project.spring.model.dal.dbmodel.Lab;
import project.spring.model.dal.dbmodel.Student;
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
	public List<AttendenceAPI> getAllAttendences() {
		List<AttendenceAPI> attendencesDto = new ArrayList<AttendenceAPI>();
		List<Attendence> attendences = new ArrayList<Attendence>();
		attendenceRepository.findAll().forEach(attendences::add);
		attendencesDto = attendences.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return attendencesDto;
	}

	@Override
	public AttendenceAPI getAttendenceById(Long id) {
		Attendence attendence = attendenceRepository.findOne(id);
		if (attendence != null) {
			return mapDto(attendence);
		} else {
			return null;
		}

	}

	@Override
	public boolean saveAttendence(AttendenceAPI attendence) {
		Attendence attendenceToSave = this.map(attendence);
		Student student = studentRepository.findOne(attendence.getStudentId());
		Lab lab = labRepository.findOne(attendence.getLaboratoryId());
		if ((lab != null) && (student != null)) {
			Attendence a = attendenceRepository.findByLabLaboratoryIdAndStudentStudentId(lab.getLaboratoryId(),
					student.getStudentId());
			if (a == null) {
				attendenceToSave.setStudent(student);
				attendenceToSave.setLaboratory(lab);
				attendenceRepository.save(attendenceToSave);
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean updateAttendence(Long id, AttendenceAPI attendence) {
		Attendence attendenceToUpdate = attendenceRepository.findOne(id);
		if (attendenceToUpdate != null) {
			Student student  = studentRepository.findOne(attendence.getStudentId());
			Lab lab = labRepository.findOne(attendence.getLaboratoryId());
			attendenceToUpdate = map(attendence);
			attendenceToUpdate.setAttendenceId(id);
			attendenceToUpdate.setLaboratory(lab);
			attendenceToUpdate.setStudent(student);
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

	public AttendenceAPI mapDto(Attendence attendence) {
		AttendenceAPI attendenceDto = new AttendenceAPI();
		attendenceDto.setLaboratoryId(attendence.getLaboratory().getLaboratoryId());
		attendenceDto.setStudentId(attendence.getStudent().getStudentId());
		return attendenceDto;
	}

	public Attendence map(AttendenceAPI attendence) {
		Attendence att = new Attendence();
		return att;
	}

}
