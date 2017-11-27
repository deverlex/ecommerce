package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class Company extends BaseDomain {

	private static final long serialVersionUID = 179847724L;

	public static final String TABLE = "company";

	private long id;
	private String fcmToken;
	private int state;
	private int level;
	private String name;
	private String address;
	
	private Date createdTime;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;

	public Company() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
}
