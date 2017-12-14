package vn.needy.ecommerce.api.v1.user.request;

import java.io.Serializable;

public class ResetPasswordReq implements Serializable {

	private static final long serialVersionUID = 143252452525L;
	
	private String firebaseToken;
	private String password;
	
	public ResetPasswordReq() {
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
