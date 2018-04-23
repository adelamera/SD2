package project.spring.model.business.service;

import java.util.List;

import project.spring.model.business.apimodel.UserDto;
import project.spring.model.dal.model.User;

public interface IUserService {

	List<User> getAllUsers();

	User getUserById(Long id);

	void saveUser(UserDto user);

	void updateUser(Long id, UserDto user);

	void deleteUser(Long id);

}
