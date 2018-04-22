package project.spring.model.dal.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.spring.model.business.model.Teacher;

@Repository
@Transactional
public interface ITeacherRepository extends CrudRepository<Teacher, Long> {

	public Teacher findByUsername(String username);

}
