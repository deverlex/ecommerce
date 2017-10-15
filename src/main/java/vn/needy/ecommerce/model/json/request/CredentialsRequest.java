package vn.needy.ecommerce.model.json.request;

import java.io.Serializable;

public class CredentialsRequest implements Serializable {
	
	private static final long serialVersionUID = -84459548965154778L;

    private String username;
    private String password;

    public CredentialsRequest() {
        super();
    }

    public CredentialsRequest(String username, String password) {
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
