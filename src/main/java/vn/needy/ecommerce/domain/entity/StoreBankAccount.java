package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class StoreBankAccount extends BaseDomain {

	private static final long serialVersionUID = 153452723L;
	
	public static final String TABLE = "store_bank_account";
	
	private long id;
	private long storeId;
	private String creditAccount;
	private String beneficiaryName;
	private String beneficiaryBankName;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	
	public StoreBankAccount() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}

	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
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
