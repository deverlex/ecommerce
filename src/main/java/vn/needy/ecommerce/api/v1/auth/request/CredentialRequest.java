package vn.needy.ecommerce.api.v1.auth.request;

import java.io.Serializable;

public class CredentialRequest implements Serializable {
	
	private static final long serialVersionUID = -84459548965154778L;

    private String username;
    private String password;

    public CredentialRequest() {
        super();
    }

    public CredentialRequest(String username, String password) {
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
