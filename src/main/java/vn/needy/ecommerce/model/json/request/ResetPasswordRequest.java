package vn.needy.ecommerce.model.json.request;

import java.io.Serializable;

public class ResetPasswordRequest implements Serializable {

	private static final long serialVersionUID = 143252452525L;
	
	private String firebaseToken;
	private String password;
	
	public ResetPasswordRequest() {
		super();
	}

	public String getFirebaseToken() {
		return firebaseToken;
	}

	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
