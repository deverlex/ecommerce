package vn.needy.ecommerce.model.json;

import java.util.Date;
import java.util.List;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.annotation.JsonFormat;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.domain.BaseFile;
import vn.needy.ecommerce.domain.entity.Store;
import vn.needy.ecommerce.domain.mongo.StoreDetail;

public class StoreJson {
	
	private String id;
	private int state;
	private int status;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date unlockTime;
	private String name;
	private String address;
	private String description;
	private String avatar;
	private List<String> coverPicture;
	private float lat;
	private float lng;
	@JsonFormat(pattern = "HH:mm:ss")
	private Date openingTime;
	@JsonFormat(pattern = "HH:mm:ss")
	private Date closingTime;
	private Date createdTime;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	
	public StoreJson() {
		super();
	}
	
	public StoreJson(@Nonnull Store store,@Nonnull StoreDetail detail) {
		id = CipherID.encrypt(store.getId());
		state = store.getState();
		status = store.getStatus();
		unlockTime = store.getUnlockTime();
		name = store.getName();
		address = store.getAddress();
		lat = store.getLat();
		lng = store.getLng();
		openingTime = store.getOpeningTime();
		closingTime = store.getClosingTime();
		createdTime = store.getCreatedTime();
		lastUpdatedTime = store.getLastUpdatedTime();
		lastUpdatedBy = store.getLastUpdatedBy();
		
		description = detail.getDescription();
		BaseFile file = detail.getAvatar();
		avatar = file.getHost() + "/" + file.getUri() + "/" + file.getFileName();
		coverPicture = detail.getCoverPicture();
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<String> getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(List<String> coverPicture) {
		this.coverPicture = coverPicture;
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
