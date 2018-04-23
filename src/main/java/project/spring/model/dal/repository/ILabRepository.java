package project.spring.model.dal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.spring.model.dal.model.Lab;

@Repository
@Transactional
public interface ILabRepository extends CrudRepository<Lab, Long> {

	public List<Lab> findAllByCurriculaContaining(String keyword);

	public List<Lab> findAllByDescriptionContaining(String keyword);

	public Lab findByLaboratoryNr(int nr);

}
