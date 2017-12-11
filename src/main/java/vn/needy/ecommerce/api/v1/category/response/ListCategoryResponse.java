package vn.needy.ecommerce.api.v1.category.response;

import java.util.List;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.CategoryWrapper;

public class ListCategoryResponse extends BaseResponse {

	private static final long serialVersionUID = 13242722279L;
	
	private List<CategoryWrapper> categories;
	
	public ListCategoryResponse() {
		super();
	}

	public List<CategoryWrapper> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryWrapper> categories) {
		this.categories = categories;
	}
	
}
