package vn.needy.ecommerce.model.json.response;

import vn.needy.ecommerce.model.base.BaseResponse;

public class CertificationResponse extends BaseResponse {

	private static final long serialVersionUID = 132424L;
	
	private String token;
	
	public CertificationResponse() {
		super();
	}
	
	public CertificationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}