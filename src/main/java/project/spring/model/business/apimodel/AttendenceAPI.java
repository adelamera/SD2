package project.spring.model.business.apimodel;

import java.io.Serializable;

public class AttendenceAPI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long studentId;
	private Long laboratoryId;

	public AttendenceAPI() {

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
