package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.base.BaseFile;

@Document(collection = "company_picture")
public class CompanyDetail implements Serializable {
	
	private static final long serialVersionUID = 153801402L;
	
	@Id
	private long companyId;
	@Field(value = "desciption")
	private String description;
	@Field(value = "site_url")
	private String siteUrl;
	@Field(value = "founded_date")
	private Date foundedDate;
	@Field(value = "opening_time")
	private Date openingTime;
	@Field(value = "closing_time")
	private Date closingTime;
	@Field(value = "avatar")
	private BaseFile avatar;
	@Field(value = "picture")
	private List<String> picture;

	public CompanyDetail() {
		super();
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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

	public BaseFile getAvatar() {
		return avatar;
	}

	public void setAvatar(BaseFile avatar) {
		this.avatar = avatar;
	}

	public List<String> getPicture() {
		return picture;
	}

	public void setPicture(List<String> picture) {
		this.picture = picture;
	}
	
}
