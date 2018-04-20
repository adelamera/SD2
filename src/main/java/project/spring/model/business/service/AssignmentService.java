package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Assignment;
import project.spring.model.dal.dto.AssignmentDto;
import project.spring.model.dal.repository.IAssignmentRepository;

@Service
public class AssignmentService implements IAssignmentService {

	private final IAssignmentRepository assignmentRepository;

	@Autowired
	public AssignmentService(IAssignmentRepository assignmentRepository) {
		this.assignmentRepository = assignmentRepository;
	}

	@Override
	public List<AssignmentDto> getAllAssignments() {
		List<AssignmentDto> assignmentsDto = new ArrayList<AssignmentDto>();
		List<Assignment> assignments = new ArrayList<Assignment>();
		assignmentRepository.findAll().forEach(assignments::add);
		assignmentsDto = assignments.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return assignmentsDto;
	}

	@Override
	public AssignmentDto getAssignmentById(Long id) {
		Assignment assignment = assignmentRepository.findOne(id);
		return mapDto(assignment);
	}

	@Override
	public void saveAssignment(AssignmentDto assignment) {
		Assignment assignmentToSave = this.map(assignment);
		assignmentRepository.save(assignmentToSave);

	}

	@Override
	public void updateAssignment(Long id, AssignmentDto assignment) {
		Assignment assignmentToUpdate = assignmentRepository.findOne(id);
		if (assignmentToUpdate != null) {
			assignmentToUpdate = map(assignment);
			assignmentToUpdate.setAssignmentId(id);
			assignmentRepository.save(assignmentToUpdate);
		}

	}

	@Override
	public void deleteAssignment(Long id) {
		assignmentRepository.delete(id);

	}

	public AssignmentDto mapDto(Assignment assignment) {
		AssignmentDto assignmentDto = new AssignmentDto(assignment.getName(), assignment.getDeadline(),
				assignment.getDescription());
		return assignmentDto;
	}

	public Assignment map(AssignmentDto assignment) {
		Assignment assign = new Assignment(assignment.getName(), assignment.getDeadline(), assignment.getDescription());
		return assign;
	}

}
