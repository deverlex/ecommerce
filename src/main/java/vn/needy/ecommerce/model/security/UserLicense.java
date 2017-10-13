package vn.needy.ecommerce.model.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import vn.needy.ecommerce.model.enums.UserState;

public class UserLicense implements UserDetails {
	
	private static final long serialVersionUID = 183873736528L;

	private String username;
	private String password;
	private int state;
	private Collection<? extends GrantedAuthority> authorities;
	private Date lastUpdatedPassword;
	
	public UserLicense() {
		super();
	}
	
	public UserLicense(String username, String password, int state, 
			Collection<? extends GrantedAuthority> authorities, Date lastUpdatedPassword) {
		this.username = username;
		this.password = password;
		this.state = state;
		this.authorities = authorities;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setLastUpdatedPassword(Date lastUpdatedPassword) {
		this.lastUpdatedPassword = lastUpdatedPassword;
	}
	
	public Date getLastUpdatedPassword() {
		return lastUpdatedPassword;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public int getState() {
		return this.state;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return state != UserState.LOCKED.getState();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return state != UserState.DELETED.getState();
	}
}
