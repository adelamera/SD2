package project.spring.model.dal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.spring.model.dal.model.Assignment;

@Repository
@Transactional
public interface IAssignmentRepository extends CrudRepository<Assignment, Long> {

	List<Assignment> findByLabLaboratoryId(Long laboratoryId);

}
