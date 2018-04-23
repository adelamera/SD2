package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.SubmissionAPI;

public interface ISubmissionService {

	List<SubmissionAPI> findAllSubmissions();

	List<SubmissionAPI> findAllSubmissionsOfStudent(Long studentId);

	List<SubmissionAPI> findAllSubmissionsOfAssignment(Long assignmentId);

	SubmissionAPI findSubmissionById(Long id);

	boolean saveSubmission(SubmissionAPI submission, Long studentId, Long assignmentId);

	boolean updateSubmission(Long id, int grade);

	boolean deleteSubmission(Long id);

}
