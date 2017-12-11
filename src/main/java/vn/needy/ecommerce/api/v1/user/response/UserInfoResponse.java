package vn.needy.ecommerce.api.v1.user.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.UserWrapper;

public class UserInfoResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1989372663L;
	
    private UserWrapper user;
    
    public UserInfoResponse() {
    	super();
    }

	public UserWrapper getUser() {
		return user;
	}

	public void setUser(UserWrapper user) {
		this.user = user;
	}
}
