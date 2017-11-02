package vn.needy.ecommerce.model.json.entity;

import java.io.Serializable;

import vn.needy.ecommerce.domain.entity.Category;

public class CategoryJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String category;
	private String coverPicture;
	
	public CategoryJson() {
		super();
	}
	
	public CategoryJson(Category category) {
		this.category = category.getCategory();
		this.coverPicture = category.getCoverPicture();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}
	
}
