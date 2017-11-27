package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class ProductHashTag extends BaseDomain {

	private static final long serialVersionUID = 128273535291L;

	public static final String TABLE = "product_hashtag";

	private long id;
	private long productId;
	private String hashTagName;

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

	public String getHashTagName() {
		return hashTagName;
	}

	public void setHashTagName(String hashTagName) {
		this.hashTagName = hashTagName;
	}
}
