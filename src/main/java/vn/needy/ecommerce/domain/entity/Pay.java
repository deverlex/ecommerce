package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class Pay extends BaseDomain {

	private static final long serialVersionUID = 1546365634L;

	public static final String TABLE = "pay";

	private long id;
	private long budgetId;
	private int behavior;
	private float budgetCharge;
	private String description;
	private String debitAccount;
	private String creditAccount;
	private Date createdTime;
	private long createdBy;
	
	public Pay() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}

	public int getBehavior() {
		return behavior;
	}

	public void setBehavior(int behavior) {
		this.behavior = behavior;
	}

	public float getBudgetCharge() {
		return budgetCharge;
	}

	public void setBudgetCharge(float budgetCharge) {
		this.budgetCharge = budgetCharge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(String debitAccount) {
		this.debitAccount = debitAccount;
	}

	public String getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(String creditAccount) {
		this.creditAccount = creditAccount;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	
}
