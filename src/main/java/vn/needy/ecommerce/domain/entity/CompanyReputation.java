package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class CompanyReputation extends BaseDomain {

	private static final long serialVersionUID = 166364535345345L;
	
	private long id;
	private int state;
	private long companyId;
	private String agreementNumber;
	private Date createdTime;
	private Date lastUpdatedTime;
	private long acceptedBy;
	
	public CompanyReputation() {
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

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
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

	public long getAcceptedBy() {
		return acceptedBy;
	}

	public void setAcceptedBy(long acceptedBy) {
		this.acceptedBy = acceptedBy;
	}
	
}
