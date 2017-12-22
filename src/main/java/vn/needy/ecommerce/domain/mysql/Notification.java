package vn.needy.ecommerce.domain.mysql;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class Notification extends BaseDomain {

	private static final long serialVersionUID = 17474636L;
	
	public static final String TABLE = "notification";

	private long id;
	private long senderId;
	private String refEntity;
	private long refId;
	private String refMethod;
	private String title;
	private String htmlContent;
	private Date createdTime;
	
	public Notification() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public String getRefEntity() {
		return refEntity;
	}

	public void setRefEntity(String refEntity) {
		this.refEntity = refEntity;
	}

	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}

	public String getRefMethod() {
		return refMethod;
	}

	public void setRefMethod(String refMethod) {
		this.refMethod = refMethod;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
}
