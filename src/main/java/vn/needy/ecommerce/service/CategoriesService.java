package vn.needy.ecommerce.service;

import vn.needy.ecommerce.model.json.response.CategoriesResponse;

public interface CategoriesService {
	
	CategoriesResponse getProductCategories();
	
	CategoriesResponse getProductSubCategory(String category);
	
	CategoriesResponse getCompanyProductCategory(long companyId);
}
