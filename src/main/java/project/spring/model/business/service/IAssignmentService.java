package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.AssignmentDto;

public interface IAssignmentService {

	List<AssignmentDto> getAllAssignments();

	AssignmentDto getAssignmentById(Long id);

	boolean saveAssignment(AssignmentDto Assignment, Long laboratoryId);

	boolean updateAssignment(Long id, AssignmentDto Assignment);

	boolean deleteAssignment(Long id);

	List<AssignmentDto> getAllAssignmentsForLab(Long laboratoryId);

}
