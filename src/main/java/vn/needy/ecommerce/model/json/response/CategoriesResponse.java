package vn.needy.ecommerce.model.json.response;

import java.util.List;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.entity.CategoryJson;

public class CategoriesResponse extends BaseResponse {

	private static final long serialVersionUID = 13242722279L;
	
	private List<CategoryJson> categories;
	
	public CategoriesResponse() {
		super();
	}

	public List<CategoryJson> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryJson> categories) {
		this.categories = categories;
	}
	
}
