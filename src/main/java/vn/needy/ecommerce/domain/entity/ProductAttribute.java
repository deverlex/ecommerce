package vn.needy.ecommerce.domain.entity;

import java.util.Date;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class ProductAttribute extends BaseDomain {

	private static final long serialVersionUID = 129871525L;

	public static final String TABLE = "product_attribute";
	
	private long id;
	private long productId;
	private String attribute;
	private String value;
	private Date lastUpdatedTime;

	public ProductAttribute() {
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

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
