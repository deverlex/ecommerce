package vn.needy.ecommerce.model.wrapper;

import java.io.Serializable;

public class CategoryWrapper implements Serializable {

	private static final long serialVersionUID = 153425646L;
	
	private String name;
	private String title;

	public CategoryWrapper() {
		super();
	}
	
	public CategoryWrapper(String category) {
		this.name = category;
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
