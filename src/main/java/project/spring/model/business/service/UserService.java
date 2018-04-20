package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.model.User;
import project.spring.model.dal.dto.UserDto;
import project.spring.model.dal.repository.UserRepository;

@Service
public class UserService implements IUserService {

	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void saveUser(UserDto user) {
		User userToSave = new User(user.getUsername(), user.getPassword(), user.getType());
		userRepository.save(userToSave);
	}

	@Override
	public void updateUser(Long id, UserDto user) {
		User userToUpdate = this.getUserById(id);

		if (userToUpdate != null) {
			userToUpdate.setUsername(user.getUsername());
			userToUpdate.setPassword(user.getPassword());
			userToUpdate.setType(user.getType());
			userRepository.save(userToUpdate);
		}
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);

	}
	

}
