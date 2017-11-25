package vn.needy.ecommerce.api.v1.category.service;

import vn.needy.ecommerce.api.v1.category.response.CategoriesResponse;

public interface CategoriesService {
	
	CategoriesResponse getCategoriesPriceNow();
	
	CategoriesResponse getSubCategoriesPriceNow(String category);
	
	CategoriesResponse getCompanyCategoriesPriceNow(long companyId);
	
	CategoriesResponse getCompanySubCategoriesPriceNow(long companyId, String category);
}
