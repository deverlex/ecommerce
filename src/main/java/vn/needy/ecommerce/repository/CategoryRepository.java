package vn.needy.ecommerce.repository;

import java.util.List;

import vn.needy.ecommerce.domain.entity.Category;

public interface CategoryRepository {

	List<Category> getCategoriesPriceNow();
	
	List<Category> getSubCategoriesPriceNow(String preCategory);
	
	List<Category> getCompanyCategoriesPriceNow(long companyId);
	
	List<Category> getCompanySubCategoriesPriceNow(long companyId, String preCategory);
	
	List<Category> getCategoriesPriceLater();
}
