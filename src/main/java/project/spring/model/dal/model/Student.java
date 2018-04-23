package project.spring.model.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "studentId")
	private Long studentId;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "groupNr")
	private int groupNr;

	@Column(name = "hobby")
	private String hobby;

	public Student() {

	}

	public Student(String name, String email, String username, String password, int groupNr, String hobby) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.groupNr = groupNr;
		this.hobby = hobby;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGroup() {
		return groupNr;
	}

	public void setGroup(int groupNr) {
		this.groupNr = groupNr;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String toString() {
		return "Student" + "id=" + this.studentId + '\'' + ", name='" + this.name + '\'' + ", email='" + this.email
				+ '\'' + ", group=" + this.groupNr + '\'' + ", hobby='" + this.hobby;
	}

}
