package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.model.base.BaseModel;

public class Permission extends BaseModel {

	private static final long serialVersionUID = 14538472784L;

	public static final String TABLE = "permission";

	private String permission;
	private String title;
	private String description;
	private boolean enable;
	private Date lastUpdatedTime;
	private long createdBy;
	private long lastUpdatedBy;
	
	public Permission() {
		super();
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
}
