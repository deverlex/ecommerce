package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.BaseFile;

@Document(collection = "category_detail")
public class CategoryDetail implements Serializable {
	
	private static final long serialVersionUID = 1992551901310L;
	
	@Id
	private String categoryId;
	@Field(value = "cover_picture")
	private BaseFile coverPicture;
	@Field(value = "child_category")
	private List<String> childCategory;
	@Field(value = "parent_category")
	private List<String> parentCategory;
	
	public CategoryDetail() {
		super();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BaseFile getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(BaseFile coverPicture) {
		this.coverPicture = coverPicture;
	}

	public List<String> getChildCategory() {
		return childCategory;
	}

	public void setChildCategory(List<String> childCategory) {
		this.childCategory = childCategory;
	}

	public List<String> getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(List<String> parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
