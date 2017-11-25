package vn.needy.ecommerce.api.v1.product.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AddProductRequest implements Serializable {

	private static final long serialVersionUID = 12491023L;
	
	private String category;
	private String name;
	private float price;
	private short quantity;
	private String promotion;
	private String description;
	private Map<String, String> attributes;
	private float feeTransport;
	private List<String> hashtag;

	public AddProductRequest() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public short getQuantity() {
		return quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
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

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public float getFeeTransport() {
		return feeTransport;
	}

	public void setFeeTransport(float feeTransport) {
		this.feeTransport = feeTransport;
	}

	public List<String> getHashtag() {
		return hashtag;
	}

	public void setHashtag(List<String> hashtag) {
		this.hashtag = hashtag;
	}
	
}
