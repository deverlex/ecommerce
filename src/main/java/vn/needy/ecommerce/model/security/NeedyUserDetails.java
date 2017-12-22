package vn.needy.ecommerce.model.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import vn.needy.ecommerce.model.enums.UserState;

public class NeedyUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 183873736528L;

	private long id;
	private String username;
	private String password;
	private int state;
	private Date unlockTime;
	private Collection<? extends GrantedAuthority> authorities;
	private Date lastResetPassword;
	
	public NeedyUserDetails() {
		super();
	}
	
	public NeedyUserDetails(long id, String username, String password, int state, Date unlockTime,
							Collection<? extends GrantedAuthority> authorities, Date lastUpdatedPassword) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.state = state;
		this.unlockTime = unlockTime;
		this.authorities = authorities;
		this.lastResetPassword = lastUpdatedPassword;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Date getLastResetPassword() {
		return lastResetPassword;
	}

	public void setLastResetPassword(Date lastResetPassword) {
		this.lastResetPassword = lastResetPassword;
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
		return true;
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
