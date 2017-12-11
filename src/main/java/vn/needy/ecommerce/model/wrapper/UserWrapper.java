package vn.needy.ecommerce.model.wrapper;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import vn.needy.ecommerce.domain.mysql.User;

public class UserWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	private int state;
	private String fullName;
    private String gender;
    private String address;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    private float lat;
    private float lng;
    private Date createdTime;
    private Date lastUpdatedTime;
    private Date lastResetPassword;
    
    public UserWrapper(User user) {
    	super();
    	state = user.getState();
    	fullName = user.getFullName();
    	address = user.getAddress();
    	lat = user.getLat();
    	lng = user.getLng();
    	email = user.getEmail();
    	birthday = user.getBirthday();
    	gender = user.getGender();
    	createdTime = user.getCreatedTime();
    	lastUpdatedTime = user.getLastUpdatedTime();
    	lastResetPassword = user.getLastResetPassword();
    }

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Date getLastResetPassword() {
		return lastResetPassword;
	}

	public void setLastResetPassword(Date lastResetPassword) {
		this.lastResetPassword = lastResetPassword;
	}
}
