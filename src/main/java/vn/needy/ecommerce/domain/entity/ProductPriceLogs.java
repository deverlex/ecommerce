package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.BaseDomain;

public class ProductPriceLogs extends BaseDomain {

	private static final long serialVersionUID = 123241457902L;

	private long id;
	private long productId;
	private float oldPrice;
	private Date createdTime;
	private long createdBy;
	
	public ProductPriceLogs() {
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

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	
}
