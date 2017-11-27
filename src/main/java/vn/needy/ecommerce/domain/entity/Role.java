package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class Role extends BaseDomain {

	private static final long serialVersionUID = 15246775L;
	
	public static final String TABLE = "role";

	private String name;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	
	public Role() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public static class List {
		public static final String SYSTEM = "SYSTEM";
		public static final String ADMIN = "ADMIN";
		public static final String USER = "USER";
		
		public static final String COMPANY_OWNER = "COMPANY_OWENER";
		public static final String SELLER = "SELLER";
		public static final String STORE_KEEPER = "STORE_KEEPER";
		public static final String STORE_MANAGER = "STORE_MANAGER";
		
		public static String[] CompanyOwner = {
			COMPANY_OWNER, SELLER, STORE_KEEPER, STORE_MANAGER
		};
	}
}
