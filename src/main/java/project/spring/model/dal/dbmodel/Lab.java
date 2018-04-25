package project.spring.model.dal.dbmodel;

import java.sql.Date;
//import java.util.Set;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "lab")
public class Lab {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "laboratoryId")
	private Long laboratoryId;

	@Column(name = "laboratoryNr")
	private int laboratoryNr;

	@Column(name = "date")
	private Date date;

	@Column(name = "title")
	private String title;

	@Column(name = "curricula")
	private String curricula;

	@Column(name = "description")
	private String description;
	
	/*@OneToMany(mappedBy="lab", cascade = CascadeType.ALL)
    private Set<Assignment> assignments;

    @OneToMany(mappedBy="lab", cascade = CascadeType.ALL)
    private Set<Attendence> attendence;*/

	public Lab() {

	}

	public Lab(int laboratoryNr, Date date, String title, String curricula, String description) {
		this.laboratoryNr = laboratoryNr;
		this.date = date;
		this.title = title;
		this.curricula = curricula;
		this.description = description;
	}

	public Long getLaboratoryId() {
		return this.laboratoryId;
	}

	public void setLaboratoryId(Long laboratoryId) {
		this.laboratoryId = laboratoryId;
	}

	public int getLaboratoryNr() {
		return this.laboratoryNr;
	}

	public void setLaboratoryNr(int laboratoryNr) {
		this.laboratoryNr = laboratoryNr;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;

	}

	public String getCurricula() {
		return this.curricula;
	}

	public void setCurricula(String curricula) {
		this.curricula = curricula;

	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;

	}

	public String toString() {
		return "Laboratory" + "id=" + this.laboratoryId + ", number='" + this.laboratoryNr + '\'' + ", date='"
				+ this.date + '\'' + ", title=" + this.title + '\'' + ", curricula='" + this.curricula + '\''
				+ ", description=" + this.description;
	}

}
