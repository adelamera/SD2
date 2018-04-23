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

import project.spring.model.business.apimodel.AttendenceAPI;
import project.spring.model.business.service.AttendenceService;

@RestController
public class AttendenceController {

	private final AttendenceService attendenceService;

	@Autowired
	public AttendenceController(AttendenceService attendenceService) {
		this.attendenceService = attendenceService;
	}

	@GetMapping("/attendences")
	public List<AttendenceAPI> getAllAttendences() {
		return attendenceService.getAllAttendences();
	}

	@GetMapping("/attendences/{id}")
	public ResponseEntity<AttendenceAPI> getAttendenceById(@RequestParam Long id) {
		AttendenceAPI attendence = attendenceService.getAttendenceById(id);
		if (attendence == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(attendence);
		}
	}

	@PostMapping("/attendences")
	public ResponseEntity<String> saveAttendence(@RequestBody AttendenceAPI attendenceToSave) {
		boolean saved = attendenceService.saveAttendence(attendenceToSave);
		if (saved == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The attendence is invalid");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The attendence was successfully saved");
		}
	}

	@PutMapping("/attendences/{id}")
	public ResponseEntity<String> updateAttendence(@RequestParam Long id,
			@RequestBody AttendenceAPI attendenceToUpdate) {
		boolean updated = attendenceService.updateAttendence(id, attendenceToUpdate);
		if (updated == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The attendence doesn't exist");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The attendence was successfully updated");
		}
	}

	@DeleteMapping("/attendences/{id}")
	public ResponseEntity<String> deleteAttendence(@RequestParam Long id) {
		boolean deleted = attendenceService.deleteAttendence(id);
		if (deleted == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The attendence doesn't exist");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("The attendence was successfully deleted");
		}
	}

}
