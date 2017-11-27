package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class Order extends BaseDomain {

	private static final long serialVersionUID = 1434324647643L;

	public static final String TABLE = "order";
	
	private long id;
	private long userId;
	private long storeId;
	private short status;
	private boolean isPaid;
	private boolean isGetTax;
	private short paymentMethod;
	private String note;
	private Date receiveFrom;
	private Date receiveTo;
	private float feeTransport;
	private Date transportFrom;
	private Date transportTo;
	private long receiverId;
	private Date lastUpdatedTime;
	
	public Order() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public boolean isGetTax() {
		return isGetTax;
	}

	public void setGetTax(boolean isGetTax) {
		this.isGetTax = isGetTax;
	}

	public short getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(short paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getReceiveFrom() {
		return receiveFrom;
	}

	public void setReceiveFrom(Date receiveFrom) {
		this.receiveFrom = receiveFrom;
	}

	public Date getReceiveTo() {
		return receiveTo;
	}

	public void setReceiveTo(Date receiveTo) {
		this.receiveTo = receiveTo;
	}

	public float getFeeTransport() {
		return feeTransport;
	}

	public void setFeeTransport(float feeTransport) {
		this.feeTransport = feeTransport;
	}

	public Date getTransportFrom() {
		return transportFrom;
	}

	public void setTransportFrom(Date transportFrom) {
		this.transportFrom = transportFrom;
	}

	public Date getTransportTo() {
		return transportTo;
	}

	public void setTransportTo(Date transportTo) {
		this.transportTo = transportTo;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
