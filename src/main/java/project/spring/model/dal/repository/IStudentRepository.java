package project.spring.model.dal.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.spring.model.dal.dbmodel.Student;

@Repository
@Transactional
public interface IStudentRepository extends CrudRepository<Student, Long> {

	public Student findByEmail(String email);

	public Student findByUsername(String username);

}
