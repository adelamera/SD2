package project.spring.model.dal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.model.dal.dbmodel.Submission;

@Repository
@Transactional
public interface ISubmissionRepository extends JpaRepository<Submission, Long> {

	List<Submission> findByStudentStudentId(Long studentId);

	List<Submission> findByAssignmentAssignmentId(Long assignmentId);
	
	List<Submission> findByAssignmentAssignmentIdAndStudentStudentId(Long assignmentId, Long studentId);
	
	List<Submission> getSubmissionsByAssignmentAssignmentId(Long assignmentId);
	
}
