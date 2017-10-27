package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.BaseDomain;

public class ProductStore extends BaseDomain {

	private static final long serialVersionUID = 129374247L;
	
	private long id;
	private long productId;
	private long storeId;
	private int quantity;

	public ProductStore() {
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
