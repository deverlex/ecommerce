package vn.needy.ecommerce.model.security;

import java.io.Serializable;

public class Certification implements Serializable {

	private static final long serialVersionUID = 132424L;
	
	private String token;
	private String message;
	
	public Certification() {
		super();
	}
	
	public Certification(String token, String message) {
		this.token = token;
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
