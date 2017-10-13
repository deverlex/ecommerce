package vn.needy.ecommerce.model.security;

import java.io.Serializable;

public class Credentials implements Serializable {
	
	private static final long serialVersionUID = -84459548965154778L;

    private String username;
    private String password;

    public Credentials() {
        super();
    }

    public Credentials(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
