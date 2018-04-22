package project.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.model.business.service.AttendenceService;
import project.spring.model.dal.dto.AttendenceDto;

@RestController
public class AttendenceController {

	private final AttendenceService attendenceService;

	@Autowired
	public AttendenceController(AttendenceService attendenceService) {
		this.attendenceService = attendenceService;
	}

	@GetMapping("/attendences")
	public List<AttendenceDto> getAllAttendences() {
		return attendenceService.getAllAttendences();
	}

	@GetMapping("/attendences/{id}")
	public AttendenceDto getAttendenceById(@RequestParam Long id) {
		return attendenceService.getAttendenceById(id);
	}

	@PostMapping("/attendences")
	public void saveAttendence(@RequestParam Long studentId, @RequestParam Long laboratoryId,
			@RequestBody AttendenceDto attendenceToSave) {
		attendenceService.saveAttendence(attendenceToSave, studentId, laboratoryId);
	}

	@PutMapping("/attendences/{id}")
	public void updateAttendence(@RequestParam Long id, @RequestBody AttendenceDto attendenceToUpdate) {
		attendenceService.updateAttendence(id, attendenceToUpdate);
	}

	@DeleteMapping("/attendences/{id}")
	public void deleteAttendence(@RequestParam Long id) {
		attendenceService.deleteAttendence(id);
	}

}
