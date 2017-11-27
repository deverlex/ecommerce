package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class Category extends BaseDomain {

	private static final long serialVersionUID = 164533434L;
	
	public static final String TABLE = "category";
	
	private String category;
	private boolean enable;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	
	public Category() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
