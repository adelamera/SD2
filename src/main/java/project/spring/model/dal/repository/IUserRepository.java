package project.spring.model.dal.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.spring.model.dal.dbmodel.User;

@Repository
@Transactional
public interface IUserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);

}
