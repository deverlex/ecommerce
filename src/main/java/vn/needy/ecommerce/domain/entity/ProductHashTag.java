package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class ProductHashTag extends BaseDomain {

	private static final long serialVersionUID = 128273535291L;

	public static final String TABLE = "product_hashtag";

	private long id;
	private long productId;
	private long hashTagId;

	public ProductHashTag() {
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

	public long getHashTagId() {
		return hashTagId;
	}

	public void setHashTagId(long hashTagId) {
		this.hashTagId = hashTagId;
	}
	
}
