package vn.needy.ecommerce.model.json.response;

import java.io.Serializable;

import vn.needy.ecommerce.model.json.entity.UserJson;

public class UserResponse implements Serializable {
	
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
