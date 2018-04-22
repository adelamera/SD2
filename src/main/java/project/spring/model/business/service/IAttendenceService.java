package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.AttendenceDto;

public interface IAttendenceService {
	
	List<AttendenceDto> getAllAttendences();

	AttendenceDto getAttendenceById(Long id);

	boolean saveAttendence(AttendenceDto Attendence);

	boolean updateAttendence(Long id, AttendenceDto Attendence);

	boolean deleteAttendence(Long id);

}
