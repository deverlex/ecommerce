package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class UserReport extends BaseDomain {

	private static final long serialVersionUID = 9732112422L;

	public static final String TABLE = "user_report";
	
	private long id;
	private long orderId;
	private short typeReport;
	private boolean isAccepted;
	private String description;
	private String pictures;
	private Date createdTime;
	private long acceptedBy;
	
	public UserReport() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public short getTypeReport() {
		return typeReport;
	}

	public void setTypeReport(short typeReport) {
		this.typeReport = typeReport;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public long getAcceptedBy() {
		return acceptedBy;
	}

	public void setAcceptedBy(long acceptedBy) {
		this.acceptedBy = acceptedBy;
	}
	
}
