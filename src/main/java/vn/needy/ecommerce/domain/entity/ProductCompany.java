package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class ProductCompany extends BaseDomain {

	private static final long serialVersionUID = 129374247L;

	public static final String TABLE = "ProductCompany";
	
	private long id;
	private long productId;
	private long companyId;
	private int quantity;
	private float price;
	private float prePrice;
	
	private String promotion;
	private String description;
	private String image;
	private String pictures;
	private String feeTransport;
	private Date lastUpdatedTime;
	private long lastUpdatedBy;
	

	public ProductCompany() {
		super();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public float getPrePrice() {
		return prePrice;
	}


	public void setPrePrice(float prePrice) {
		this.prePrice = prePrice;
	}


	public String getPromotion() {
		return promotion;
	}


	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getPictures() {
		return pictures;
	}


	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getFeeTransport() {
		return feeTransport;
	}

	public void setFeeTransport(String feeTransport) {
		this.feeTransport = feeTransport;
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
