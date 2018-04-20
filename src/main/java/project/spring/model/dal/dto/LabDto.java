package project.spring.model.dal.dto;

import java.io.Serializable;
import java.sql.Date;

public class LabDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int laboratoryNr;
	private Date date;
	private String title;
	private String curricula;
	private String description;

	public LabDto() {

	}

	public LabDto(int laboratoryNr, Date date, String title, String curricula, String description) {
		this.laboratoryNr = laboratoryNr;
		this.date = date;
		this.curricula = curricula;
		this.description = description;
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
		return "Laboratory" + "number='" + this.laboratoryNr + '\'' + ", date='" + this.date + '\'' + ", title="
				+ this.title + '\'' + ", curricula='" + this.curricula + '\'' + ", description=" + this.description;
	}

}
