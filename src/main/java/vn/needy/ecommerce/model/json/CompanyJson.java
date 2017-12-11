package vn.needy.ecommerce.model.json;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.domain.mysql.Company;

public class CompanyJson implements Serializable {
	
	private static final long serialVersionUID = 153543535L;
	
	private String id;
	private String companyNumber;
	private int state;
	private String name;
	private String address;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date foundedDate;
	@JsonFormat(pattern = "HH:mm")
	private Date openingTime;
	@JsonFormat(pattern = "HH:mm")
	private Date closingTime;
	private String description;
	private String siteUrl;
	private Date createdTime;
	private Date lastUpdatedTime;
	private boolean isReputation;
	private String email;
	private float lat;
	private float lng;
	
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
		this.email = company.getEmail();
		this.foundedDate = company.getFoundedDate();
		this.openingTime = company.getOpeningTime();
		this.closingTime = company.getClosingTime();
		this.description = company.getDescription();
		this.siteUrl = company.getSiteUrl();
		this.lat = company.getLat();
		this.lng = company.getLng();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
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
}
