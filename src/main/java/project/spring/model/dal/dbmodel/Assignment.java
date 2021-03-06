package project.spring.model.dal.dbmodel;

import java.sql.Date;
//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "assignmentId")
	private Long assignmentId;
	
	@ManyToOne
	@JoinColumn(name = "laboratoryId")
	private Lab lab;

	@Column(name = "name")
	private String name;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "description")
	private String description;
	
	/*@OneToMany(mappedBy="assignment", cascade = CascadeType.ALL)
    private Set<Submission> submissions;*/

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

	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	public String toString() {
		return "Assignment" + "id=" + this.assignmentId + ", name='" + this.name + '\'' + ", deadline='" + this.deadline
				+ '\'' + ", description=" + this.description;
	}

}
