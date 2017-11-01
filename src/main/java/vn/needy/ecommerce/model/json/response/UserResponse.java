package vn.needy.ecommerce.model.json.response;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.entity.UserJson;

public class UserResponse extends BaseResponse {
	
	private static final long serialVersionUID = 1989372663L;
	
    private UserJson user;
    
    public UserResponse() {
    	super();
    }

	public UserJson getUser() {
		return user;
	}

	public void setUser(UserJson user) {
		this.user = user;
	}
}
