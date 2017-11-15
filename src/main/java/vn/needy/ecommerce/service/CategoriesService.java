package vn.needy.ecommerce.service;

import vn.needy.ecommerce.model.json.response.CategoriesResponse;

public interface CategoriesService {
	
	CategoriesResponse getCategoriesPriceNow();
	
	CategoriesResponse getSubCategoriesPriceNow(String category);
	
	CategoriesResponse getCompanyCategoriesPriceNow(long companyId);
	
	CategoriesResponse getCompanySubCategoriesPriceNow(long companyId, String category);
}
