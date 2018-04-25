package project.spring.model.dal.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "submission")
public class Submission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "submissionId")
	private Long submissionId;

	@ManyToOne
	@JoinColumn(name = "studentId")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "assignmentId")
	private Assignment assignment;

	@Column(name = "grade")
	private int grade;

	@Column(name = "link")
	private String link;

	@Column(name = "remark")
	private String remark;

	public Submission() {

	}

	public Submission(int grade, String link, String remark) {
		this.grade = grade;
		this.link = link;
		this.remark = remark;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		return "Submission" + "id=" + this.submissionId + '\'' + ", student='" + this.student.getName() + '\''
				+ ", assignment='" + this.assignment.getName() + '\'' + ", link='" + this.link + '\'' + ", remark='"
				+ this.remark;
	}

}
