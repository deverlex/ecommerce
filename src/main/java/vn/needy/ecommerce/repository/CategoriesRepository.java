package vn.needy.ecommerce.repository;

import java.util.List;

import vn.needy.ecommerce.domain.entity.Category;

public interface CategoriesRepository {

	List<Category> getProductCategories();
	
	List<Category> getProductSubCategory(String category);
	
	List<Category> getCompanyProductCategory(long companyId);
	
	List<Category> getCompanyProductSubCategory(long companyId, String category);
	
	List<Category> getServiceCategories();
}
