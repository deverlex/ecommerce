package vn.needy.ecommerce.api.v1.user.response;

import vn.needy.ecommerce.model.wrapper.UserWrapper;

import java.io.Serializable;

public class LoginResp implements Serializable {

    private static final long serialVersionUID = 132424L;

    private UserWrapper user;
    private String token;

    public LoginResp(UserWrapper user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserWrapper getUser() {
        return user;
    }

    public void setUser(UserWrapper user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
