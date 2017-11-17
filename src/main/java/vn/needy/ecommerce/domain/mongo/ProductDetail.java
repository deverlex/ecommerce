package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.BaseFile;

@Document(collection = "product_detail")
public class ProductDetail implements Serializable {

	private static final long serialVersionUID = 29735511138209731L;
	
	@Id
	private long productId;
	@Field(value = "category_id")
	private String categoryId;
	@Field(value = "store_id")
	private long storeId;
	private String name;
	private String gift;
	private String description;
	private Map<String, String> attribute;
	@Field(value = "cover_picture")
	private BaseFile coverPicture;
	@Field(value = "parent_product")
	private List<Long> parentProduct;
	@Field(value = "child_product")
	private List<Long> childProduct;
	
	public ProductDetail() {
		super();
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	public BaseFile getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(BaseFile coverPicture) {
		this.coverPicture = coverPicture;
	}

	public List<Long> getParentProduct() {
		return parentProduct;
	}

	public void setParentProduct(List<Long> parentProduct) {
		this.parentProduct = parentProduct;
	}

	public List<Long> getChildProduct() {
		return childProduct;
	}

	public void setChildProduct(List<Long> childProduct) {
		this.childProduct = childProduct;
	}
	
}
