package vn.needy.ecommerce.api.v1.category.json;

import java.io.Serializable;

import vn.needy.ecommerce.domain.entity.Category;

public class CategoryJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String title;

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

}
