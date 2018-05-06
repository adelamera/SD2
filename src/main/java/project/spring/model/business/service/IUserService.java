package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.UserAPI;
import project.spring.model.dal.dbmodel.User;

public interface IUserService {

	List<User> getAllUsers();

	User getUserById(Long id);
	
	User getUserByUsername(String username);

	boolean saveUser(UserAPI user);

	boolean updateUser(Long id, UserAPI user);

	boolean deleteUser(Long id);
	
	String login(String username, String password);

}
