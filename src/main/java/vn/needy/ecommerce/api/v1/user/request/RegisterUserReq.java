package vn.needy.ecommerce.api.v1.user.request;

import java.io.Serializable;

public class RegisterUserReq implements Serializable {

	private static final long serialVersionUID = 13883736L;
	
	//User ID of Firebase Auth
	private String firebaseUid;
	//Access token
	private String firebaseToken;
	private String username;
	private String password;
	
	public RegisterUserReq() {
		super();
	}

	public String getFirebaseUid() {
		return firebaseUid;
	}

	public void setFirebaseUid(String firebaseUid) {
		this.firebaseUid = firebaseUid;
	}

	public String getFirebaseToken() {
		return firebaseToken;
	}

	public void setFirebaseToken(String firebaseToken) {
		this.firebaseToken = firebaseToken;
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

}
