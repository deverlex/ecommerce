package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.base.BaseDomain;

import java.util.Date;

public class NotificationUser extends BaseDomain {

	private static final long serialVersionUID = 121298012343L;

	public static final String TABLE  = "notification_user";
	
	private long id;
	private long notificationId;
	private long receiverId;
	private boolean isRead;
	private boolean isView;
	private Date lastUpdatedTime;
	
	public NotificationUser() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
}
