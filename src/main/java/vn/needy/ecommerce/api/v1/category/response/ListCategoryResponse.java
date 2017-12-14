package vn.needy.ecommerce.api.v1.category.response;

import java.io.Serializable;
import java.util.List;

import vn.needy.ecommerce.model.wrapper.CategoryWrapper;

public class ListCategoryResponse implements Serializable {

	private static final long serialVersionUID = 13242722279L;
	
	private List<CategoryWrapper> categories;
	
	public ListCategoryResponse() {
		super();
	}

	public ListCategoryResponse(List<CategoryWrapper> categories) {
		this.categories = categories;
	}

	public List<CategoryWrapper> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryWrapper> categories) {
		this.categories = categories;
	}
	
}
