package vn.needy.ecommerce.api.v1.user.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.json.UserJson;

public class UserInfoResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1989372663L;
	
    private UserJson user;
    
    public UserInfoResponse() {
    	super();
    }

	public UserJson getUser() {
		return user;
	}

	public void setUser(UserJson user) {
		this.user = user;
	}
}
