package vn.needy.ecommerce.api.v1.user.response;

import java.io.Serializable;

public class TokenResponse implements Serializable {

	private static final long serialVersionUID = 132424L;
	
	private String token;

	public TokenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
