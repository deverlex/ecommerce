package vn.needy.ecommerce.model.json;

import java.io.Serializable;

import vn.needy.ecommerce.domain.entity.Category;

public class CategoryJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String title;
	private String coverPicture;
	
	public CategoryJson() {
		super();
	}
	
	public CategoryJson(Category category) {
		this.name = category.getName();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}
	
}
