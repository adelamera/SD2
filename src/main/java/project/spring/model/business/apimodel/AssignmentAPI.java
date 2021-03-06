package project.spring.model.business.apimodel;

import java.io.Serializable;
import java.sql.Date;

public class AssignmentAPI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date deadline;
	private String description;

	public AssignmentAPI() {

	}

	public AssignmentAPI(String name, Date deadline, String description) {
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
		return "Assignment" + System.lineSeparator() + 
				"name = " + this.name + System.lineSeparator() + 
				"deadline = " + this.deadline + System.lineSeparator() +
				"description = " + this.description;
	}
}
