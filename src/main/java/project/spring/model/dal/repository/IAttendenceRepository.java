package project.spring.model.dal.repository;

import org.springframework.data.repository.CrudRepository;

import project.spring.model.business.model.Attendence;

public interface IAttendenceRepository extends CrudRepository<Attendence, Long> {

}
