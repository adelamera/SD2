package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.AssignmentDto;

public interface IAssignmentService {

	List<AssignmentDto> getAllAssignments();

	AssignmentDto getAssignmentById(Long id);

	void saveAssignment(AssignmentDto Assignment);

	void updateAssignment(Long id, AssignmentDto Assignment);

	void deleteAssignment(Long id);

}
