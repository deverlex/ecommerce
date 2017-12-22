package vn.needy.ecommerce.domain.mysql;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class CompanyGuarantee extends BaseDomain {

	private static final long serialVersionUID = 166364535345345L;
	
	public static final String TABLE = "company_guarantee";

	private long id;
	private int state;
	private long companyId;
	private Date lastUpdatedTime;
	private long changedBy;
	
	public CompanyGuarantee() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public long getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(long changedBy) {
		this.changedBy = changedBy;
	}
}
