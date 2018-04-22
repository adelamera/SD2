package project.spring.model.business.service;

import java.util.List;

import project.spring.model.dal.dto.SubmissionDto;

public interface ISubmissionService {

	List<SubmissionDto> findAllSubmissions();

	List<SubmissionDto> findAllSubmissionsOfStudent(Long studentId);

	List<SubmissionDto> findAllSubmissionsOfAssignment(Long assignmentId);

	SubmissionDto findSubmissionById(Long id);

	boolean saveSubmission(SubmissionDto submission, Long studentId, Long assignmentId);

	boolean updateSubmission(Long id, int grade);

	boolean deleteSubmission(Long id);

}
