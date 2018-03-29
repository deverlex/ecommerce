package vn.needy.ecommerce.domain.mysql;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class WalletPay extends BaseDomain {

	private static final long serialVersionUID = 1299836511984L;

	public static final String TABLE = "wallet_pay";
	
	private long id;
	private long walletId;
	private long productId;
	private int budgetCharge;
	private String description;
	private Date lastUpdatedTime;
	
	public WalletPay() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWalletId() {
		return walletId;
	}

	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getBudgetCharge() {
		return budgetCharge;
	}

	public void setBudgetCharge(int budgetCharge) {
		this.budgetCharge = budgetCharge;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
