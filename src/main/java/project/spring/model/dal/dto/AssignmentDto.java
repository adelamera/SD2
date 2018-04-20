package project.spring.model.dal.dto;

import java.io.Serializable;
import java.sql.Date;

public class AssignmentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date deadline;
	private String description;

	public AssignmentDto() {

	}

	public AssignmentDto(String name, Date deadline, String description) {
		this.name = name;
		this.deadline = deadline;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "Assignment" + "name='" + this.name + '\'' + ", deadline='" + this.deadline + '\'' + ", description="
				+ this.description;
	}

}
