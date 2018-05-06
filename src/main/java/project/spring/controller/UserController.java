package project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.model.business.apimodel.UserAPI;
import project.spring.model.business.service.UserService;
import project.spring.model.dal.dbmodel.User;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/user/id")
	public User getUserById(@RequestParam Long id) {
		return userService.getUserById(id);
	}

	@GetMapping("/user/username")
	public User getUserByUsername(@RequestParam String username) {
		return userService.getUserByUsername(username);
	}

	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody UserAPI userToSave) {
		boolean saved = userService.saveUser(userToSave);
		if (saved) {
			return ResponseEntity.status(HttpStatus.OK).body("The user was saved");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user is invalid");
		}
	}

	@PostMapping("/user/username/password")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		String type = userService.login(username, password);
		if ((type.equals("student")) || (type.equals("teacher"))) {
			return ResponseEntity.status(HttpStatus.OK).body(type);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
		}
	}

	@PutMapping("/user/id")
	public ResponseEntity<String> updateUser(@RequestParam Long id, @RequestBody UserAPI userToUpdate) {
		boolean updated = userService.updateUser(id, userToUpdate);
		if (updated) {
			return ResponseEntity.status(HttpStatus.OK).body("The user was updated");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user is invalid");
		}
	}

	@DeleteMapping("user/id")
	public ResponseEntity<String> deleteUser(@RequestParam Long id) {
		boolean deleted = userService.deleteUser(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.OK).body("The user was deleted");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user doesn't exist");
		}
	}

}
