package project.spring.model.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.model.business.apimodel.UserAPI;
import project.spring.model.dal.dbmodel.User;
import project.spring.model.dal.repository.IUserRepository;
import project.spring.utils.PasswordEncryptor;

@Service
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	@Autowired
	public UserService(IUserRepository userRepository) {
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
	public boolean saveUser(UserAPI user) {
		User userToSave = new User(user.getUsername(), user.getPassword(), user.getType());
		if (userRepository.findByUsername(userToSave.getUsername()) != null) {
			return false;
		} else if ((user.getType().equals("student")) || (user.getType().equals("teacher"))) {
			userToSave.setPassword(PasswordEncryptor.setPasswordEncrypt(userToSave.getPassword()));
			userRepository.save(userToSave);
			return true;
		}
		return false;

	}

	@Override
	public boolean updateUser(Long id, UserAPI user) {
		User userToUpdate = this.getUserById(id);
		if (userToUpdate != null) {
			if (userRepository.findByUsername(user.getUsername()) == null) {
				userToUpdate.setUsername(user.getUsername());
				userToUpdate.setPassword(PasswordEncryptor.setPasswordEncrypt(user.getPassword()));
				userToUpdate.setType(user.getType());
				userRepository.save(userToUpdate);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteUser(Long id) {
		User userToDelete = userRepository.findOne(id);
		if (userToDelete != null) {
			userRepository.delete(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String login(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			if (user.getPassword().equals(PasswordEncryptor.setPasswordEncrypt(password))) {
				return user.getType();
			}
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
