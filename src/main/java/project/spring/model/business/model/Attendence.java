package project.spring.model.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendence")
public class Attendence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attendenceId")
	private Long attendenceId;

	@ManyToOne
	@JoinColumn(name = "laboratoryId")
	private Lab lab;

	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student student;

	public Attendence() {

	}

	public Long getAttendenceId() {
		return attendenceId;
	}

	public void setAttendenceId(Long attendenceId) {
		this.attendenceId = attendenceId;
	}

	public Lab getLaboratory() {
		return lab;
	}

	public void setLaboratory(Lab lab) {
		this.lab = lab;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
