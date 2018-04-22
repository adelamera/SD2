package project.spring.model.dal.dto;

import java.io.Serializable;

public class SubmissionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int grade;
	private String link;
	private String remark;

	public SubmissionDto() {

	}

	public SubmissionDto(int grade, String link, String remark) {
		this.grade = grade;
		this.link = link;
		this.remark = remark;
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
		return "Submission" + "student='" + '\'' + ", link='" + this.link + '\'' + ", remark='" + this.remark;
	}

}
