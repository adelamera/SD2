package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.apimodel.AssignmentAPI;
import project.spring.model.dal.model.Assignment;
import project.spring.model.dal.model.Lab;
import project.spring.model.dal.repository.IAssignmentRepository;
import project.spring.model.dal.repository.ILabRepository;

@Service
public class AssignmentService implements IAssignmentService {

	private final IAssignmentRepository assignmentRepository;
	private final ILabRepository laboratoryRepository;

	@Autowired
	public AssignmentService(IAssignmentRepository assignmentRepository, ILabRepository laboratoryRepository) {
		this.assignmentRepository = assignmentRepository;
		this.laboratoryRepository = laboratoryRepository;
	}

	@Override
	public List<AssignmentAPI> getAllAssignments() {
		List<AssignmentAPI> assignmentsDto = new ArrayList<AssignmentAPI>();
		List<Assignment> assignments = new ArrayList<Assignment>();
		assignmentRepository.findAll().forEach(assignments::add);
		assignmentsDto = assignments.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return assignmentsDto;
	}

	@Override
	public AssignmentAPI getAssignmentById(Long id) {
		Assignment assignment = assignmentRepository.findOne(id);
		if (assignment != null) {
			return mapDto(assignment);
		} else {
			return null;
		}
	}

	@Override
	public boolean saveAssignment(AssignmentAPI assignment, Long laboratoryId) {
		Assignment assignmentToSave = this.map(assignment);
		Lab lab = laboratoryRepository.findOne(laboratoryId);
		if (lab != null) {
			assignmentToSave.setLab(lab);
			assignmentRepository.save(assignmentToSave);
			return true;
		}
		return false;

	}

	@Override
	public boolean updateAssignment(Long id, AssignmentAPI assignment) {
		Assignment assignmentToUpdate = assignmentRepository.findOne(id);
		if (assignmentToUpdate != null) {
			Lab lab = assignmentToUpdate.getLab();
			assignmentToUpdate = map(assignment);
			assignmentToUpdate.setAssignmentId(id);
			assignmentToUpdate.setLab(lab);
			assignmentRepository.save(assignmentToUpdate);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteAssignment(Long id) {
		Assignment assignment = assignmentRepository.findOne(id);
		if (assignment != null) {
			assignmentRepository.delete(id);
			return true;
		}
		return false;

	}

	@Override
	public List<AssignmentAPI> getAllAssignmentsForLab(Long laboratoryId) {
		List<AssignmentAPI> assignmentsDto = new ArrayList<AssignmentAPI>();
		List<Assignment> assignments = new ArrayList<Assignment>();
		Lab lab = laboratoryRepository.findOne(laboratoryId);
		if (lab != null) {
			assignmentRepository.findByLabLaboratoryId(laboratoryId).forEach(assignments::add);
			assignmentsDto = assignments.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
			return assignmentsDto;
		}
		return null;
	}

	public AssignmentAPI mapDto(Assignment assignment) {
		AssignmentAPI assignmentDto = new AssignmentAPI(assignment.getName(), assignment.getDeadline(),
				assignment.getDescription());
		return assignmentDto;
	}

	public Assignment map(AssignmentAPI assignment) {
		Assignment assign = new Assignment(assignment.getName(), assignment.getDeadline(), assignment.getDescription());
		return assign;
	}

}
