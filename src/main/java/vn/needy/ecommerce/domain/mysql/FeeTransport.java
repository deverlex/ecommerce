package vn.needy.ecommerce.domain.mysql;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class FeeTransport extends BaseDomain {

	private static final long serialVersionUID = 1545343535L;
	
	public static final String TABLE = "fee_transport";
	
	private long id;
	private long storeId;
	private short feeType;
	private float from;
	private float to;
	private float fee;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	
	public FeeTransport() {
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

	public short getFeeType() {
		return feeType;
	}

	public void setFeeType(short feeType) {
		this.feeType = feeType;
	}

	public float getFrom() {
		return from;
	}

	public void setFrom(float from) {
		this.from = from;
	}

	public float getTo() {
		return to;
	}

	public void setTo(float to) {
		this.to = to;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
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
