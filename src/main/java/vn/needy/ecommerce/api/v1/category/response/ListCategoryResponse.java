package vn.needy.ecommerce.api.v1.category.response;

import java.util.List;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.category.json.CategoryJson;

public class ListCategoryResponse extends BaseResponse {

	private static final long serialVersionUID = 13242722279L;
	
	private List<CategoryJson> categories;
	
	public ListCategoryResponse() {
		super();
	}

	public List<CategoryJson> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryJson> categories) {
		this.categories = categories;
	}
	
}
