package project.spring.model.business.apimodel;

import java.io.Serializable;

public class UserAPI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String type;

	public UserAPI() {

	}

	public UserAPI(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		if ((type.equals("student")) || (type.equals("teacher"))) {
			this.type = type;
		}
	}

	public String toString() {
		return "User: " + this.getUsername();
	}

}
