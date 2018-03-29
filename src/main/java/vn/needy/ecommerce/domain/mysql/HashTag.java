package vn.needy.ecommerce.domain.mysql;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class HashTag extends BaseDomain {
	
	private static final long serialVersionUID = 1235322768L;

	public static final String TABLE = "hashtag";

	private String name;
	private Date createdTime;

	public HashTag() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
}
