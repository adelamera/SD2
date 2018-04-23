package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.AssignmentAPI;

public interface IAssignmentService {

	List<AssignmentAPI> getAllAssignments();

	AssignmentAPI getAssignmentById(Long id);

	boolean saveAssignment(AssignmentAPI Assignment, Long laboratoryId);

	boolean updateAssignment(Long id, AssignmentAPI Assignment);

	boolean deleteAssignment(Long id);

	List<AssignmentAPI> getAllAssignmentsForLab(Long laboratoryId);

}
