package project.spring.model.dal.dto;

import java.io.Serializable;

public class AttendenceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long studentId;
	private Long laboratoryId;

	public AttendenceDto() {

	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}
	

}
