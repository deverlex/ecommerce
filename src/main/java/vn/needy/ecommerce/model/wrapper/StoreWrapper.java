package vn.needy.ecommerce.model.wrapper;

import java.util.Date;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonFormat;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.domain.mysql.Store;

public class StoreWrapper {
	
	private String id;
	private int state;
	private int status;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date unlockTime;
	private String name;
	private String address;
	private String description;
	private String email;
	private float lat;
	private float lng;
	@JsonFormat(pattern = "HH:mm")
	private Date openingTime;
	@JsonFormat(pattern = "HH:mm")
	private Date closingTime;
	private Date createdTime;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	
	public StoreWrapper() {
		super();
	}
	
	public StoreWrapper(@Nonnull Store store) {
		id = CipherID.encrypt(store.getId());
		state = store.getState();
		status = store.getStatus();
		unlockTime = store.getUnlockTime();
		name = store.getName();
		address = store.getAddress();
		lat = store.getLat();
		lng = store.getLng();
		email = store.getEmail();
		description = store.getDescription();
		openingTime = store.getOpeningTime();
		closingTime = store.getClosingTime();
		createdTime = store.getCreatedTime();
		lastUpdatedTime = store.getLastUpdatedTime();
		lastUpdatedBy = store.getLastUpdatedBy();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public Date getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
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

	public long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
}
