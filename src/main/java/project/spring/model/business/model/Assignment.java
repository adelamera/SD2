package project.spring.model.business.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assignmentId")
	private Long assignmentId;

	@Column(name = "name")
	private String name;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "description")
	private String description;

	public Assignment() {

	}

	public Assignment(String name, Date deadline, String description) {
		this.name = name;
		this.deadline = deadline;
		this.description = description;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
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
		return "Assignment" + "id=" + this.assignmentId + ", name='" + this.name + '\'' + ", deadline='" + this.deadline
				+ '\'' + ", description=" + this.description;
	}

}
