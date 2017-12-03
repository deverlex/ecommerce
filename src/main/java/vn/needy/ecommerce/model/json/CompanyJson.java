package vn.needy.ecommerce.model.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.domain.entity.Company;

public class CompanyJson implements Serializable {
	
	private static final long serialVersionUID = 153543535L;
	
	private String id;
	private String companyNumber;
	private int state;
	private String name;
	private String address;
	private Date foundedDate;
	@JsonFormat(pattern = "HH:mm:ss")
	private Date openingTime;
	@JsonFormat(pattern = "HH:mm:ss")
	private Date closingTime;
	private String avatar;
	private List<String> pictures;
	private String description;
	private String siteUrl;
	private Date createdTime;
	private Date lastUpdatedTime;
	private boolean isReputation;
	
	ObjectMapper mapper;
	
	public CompanyJson() {
		super();
	}
	
	public CompanyJson(Company company) {
		this.id = CipherID.encrypt(company.getId());
		this.state = company.getState();
		this.name = company.getName();
		this.address = company.getAddress();
		this.createdTime = company.getCreatedTime();
		this.lastUpdatedTime = company.getLastUpdatedTime();
		this.isReputation = false;
	}

	public String getId() {
		return id;
	}

	public void setId(long id) {
		this.id = CipherID.encrypt(id);
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String address() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(Date foundedDate) {
		this.foundedDate = foundedDate;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
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

	public boolean isReputation() {
		return isReputation;
	}

	public void setReputation(boolean isReputation) {
		this.isReputation = isReputation;
	}
	
}