package vn.needy.ecommerce.api.v1.user.response;

import vn.needy.ecommerce.model.wrapper.UserWrapper;

import java.io.Serializable;

public class UserInfoResponse implements Serializable {
	
	private static final long serialVersionUID = 1989372663L;
	
    private UserWrapper user;
    
    public UserInfoResponse() {
    	super();
    }

	public UserInfoResponse(UserWrapper wrapper) {
		this.user = wrapper;
	}

	public UserWrapper getUser() {
		return user;
	}

	public void setUser(UserWrapper user) {
		this.user = user;
	}
}
