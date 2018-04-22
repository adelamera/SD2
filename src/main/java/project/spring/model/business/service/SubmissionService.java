package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.Assignment;
import project.spring.model.business.model.Student;
import project.spring.model.business.model.Submission;
import project.spring.model.dal.dto.SubmissionDto;
import project.spring.model.dal.repository.IAssignmentRepository;
import project.spring.model.dal.repository.IStudentRepository;
import project.spring.model.dal.repository.ISubmissionRepository;
import project.spring.utils.DateComparator;

@Service
public class SubmissionService implements ISubmissionService {

	private final ISubmissionRepository submissionRepository;
	private final IStudentRepository studentRepository;
	private final IAssignmentRepository assignmentRepository;

	@Autowired
	public SubmissionService(ISubmissionRepository submissionRepository, IStudentRepository studentRepository,
			IAssignmentRepository assignmentRepository) {
		this.submissionRepository = submissionRepository;
		this.studentRepository = studentRepository;
		this.assignmentRepository = assignmentRepository;
	}

	@Override
	public List<SubmissionDto> findAllSubmissions() {
		List<SubmissionDto> submissionsDto = new ArrayList<SubmissionDto>();
		List<Submission> submissions = new ArrayList<Submission>();
		submissionRepository.findAll().forEach(submissions::add);
		submissionsDto = submissions.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
		return submissionsDto;
	}

	@Override
	public List<SubmissionDto> findAllSubmissionsOfStudent(Long studentId) {
		List<SubmissionDto> submissionsDto = new ArrayList<SubmissionDto>();
		List<Submission> submissions = new ArrayList<Submission>();
		Student student = studentRepository.findOne(studentId);
		if (student != null) {
			submissionRepository.findByStudentStudentId(studentId).forEach(submissions::add);
			submissionsDto = submissions.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
			return submissionsDto;
		}
		return null;
	}

	@Override
	public List<SubmissionDto> findAllSubmissionsOfAssignment(Long assignmentId) {
		List<SubmissionDto> submissionsDto = new ArrayList<SubmissionDto>();
		List<Submission> submissions = new ArrayList<Submission>();
		Assignment assignment = assignmentRepository.findOne(assignmentId);
		if (assignment != null) {
			submissionRepository.findByAssignmentAssignmentId(assignmentId).forEach(submissions::add);
			System.out.println(submissions.size());
			submissionsDto = submissions.stream().map(s -> this.mapDto(s)).collect(Collectors.toList());
			return submissionsDto;
		}
		return null;
	}

	@Override
	public SubmissionDto findSubmissionById(Long id) {
		Submission submission = submissionRepository.findOne(id);
		if (submission != null) {
			return mapDto(submission);
		} else {
			return null;
		}
	}

	@Override
	public boolean saveSubmission(SubmissionDto submission, Long studentId, Long assignmentId) {
		Submission submissionToSave = map(submission);
		Student student = studentRepository.findOne(studentId);
		Assignment assignment = assignmentRepository.findOne(assignmentId);
		if ((assignment != null) && (student != null)) {
			if (DateComparator.metDeadline(assignment.getDeadline())) {
				submissionToSave.setStudent(student);
				submissionToSave.setAssignment(assignment);
				submissionToSave.setGrade(0);
				submissionRepository.save(submissionToSave);
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean updateSubmission(Long id, int grade) {
		Submission submissionToUpdate = submissionRepository.findOne(id);
		if (submissionToUpdate != null) {
			//Assignment assignment = submissionToUpdate.getAssignment();
			//Student student = submissionToUpdate.getStudent();
			if ((grade > 0) && (grade <= 10)) {
				/*
				 * submissionToUpdate.setSubmissionId(id);
				 * submissionToUpdate.setAssignment(assignment);
				 * submissionToUpdate.setStudent(student);
				 */
				submissionToUpdate.setGrade(grade);
				submissionRepository.save(submissionToUpdate);
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean deleteSubmission(Long id) {
		Submission submission = submissionRepository.findOne(id);
		if (submission != null) {
			submissionRepository.delete(id);
			return true;
		}
		return false;
	}

	public SubmissionDto mapDto(Submission submission) {
		SubmissionDto submissionDto = new SubmissionDto(submission.getGrade(), submission.getLink(),
				submission.getRemark());
		return submissionDto;
	}

	public Submission map(SubmissionDto submission) {
		Submission sub = new Submission(submission.getGrade(), submission.getLink(), submission.getRemark());
		return sub;
	}

}
