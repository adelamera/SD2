package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.AttendenceAPI;

public interface IAttendenceService {

	List<AttendenceAPI> getAllAttendences();

	List<AttendenceAPI> getByStudentId(Long studentId);

	AttendenceAPI getAttendenceById(Long id);

	boolean saveAttendence(AttendenceAPI Attendence);

	boolean updateAttendence(Long id, AttendenceAPI Attendence);

	boolean deleteAttendence(Long id);

}
