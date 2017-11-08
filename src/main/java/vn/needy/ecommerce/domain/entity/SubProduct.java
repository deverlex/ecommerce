package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.BaseDomain;

public class SubProduct extends BaseDomain {

	private static final long serialVersionUID = 19393837L;
	
	private long id;
	private long subProductId;
	private long productId;

	public SubProduct() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSubProductId() {
		return subProductId;
	}

	public void setSubProductId(long subProductId) {
		this.subProductId = subProductId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
}
