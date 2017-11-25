package vn.needy.ecommerce.api.v1.category.service;

import vn.needy.ecommerce.api.v1.category.response.ListCategoryResponse;

public interface CategoryService {
	
	ListCategoryResponse getCategoriesPriceNow();
	
	ListCategoryResponse getSubCategoriesPriceNow(String category);
	
	ListCategoryResponse getCompanyCategoriesPriceNow(long companyId);
	
	ListCategoryResponse getCompanySubCategoriesPriceNow(long companyId, String category);
}
