package project.spring.model.dal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import project.spring.model.dal.dbmodel.Attendence;

public interface IAttendenceRepository extends CrudRepository<Attendence, Long> {

	Attendence findByLabLaboratoryIdAndStudentStudentId(Long laboratoryId, Long studentId);

	List<Attendence> findByStudentStudentId(Long studentId);

}
