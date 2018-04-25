package project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.model.business.apimodel.UserDto;
import project.spring.model.business.service.UserService;
import project.spring.model.dal.dbmodel.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("getUserById")
	public User getUserById(@RequestParam Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("saveUser")
	public void saveUser(@RequestBody UserDto userToSave) {
		userService.saveUser(userToSave);
	}

	@PutMapping("updateUser")
	public void updateUser(@RequestParam Long id, @RequestBody UserDto userToUpdate) {
		userService.updateUser(id, userToUpdate);
	}

	@DeleteMapping("deleteUserById")
	public void deleteUser(@RequestParam Long id) {
		userService.deleteUser(id);
	}

}
