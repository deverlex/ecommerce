package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class SubCategory extends BaseDomain {

	private static final long serialVersionUID = 1232425189761L;

	public static final String TABLE = "SubCategories";
	
	private long id;
	private String subCategory;
	private String refCategory;
	private Date lastUpdatedTime;
	
	public SubCategory() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getRefCategory() {
		return refCategory;
	}

	public void setRefCategory(String refCategory) {
		this.refCategory = refCategory;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
