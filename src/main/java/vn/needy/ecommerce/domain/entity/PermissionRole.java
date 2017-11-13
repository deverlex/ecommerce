package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.model.base.BaseModel;

public class PermissionRole extends BaseModel {

	private static final long serialVersionUID = 145636363L;
	
	public static final String TABLE = "permisson_role";

	private long id;
	private String permission;
	private String role;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	
	public PermissionRole() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
