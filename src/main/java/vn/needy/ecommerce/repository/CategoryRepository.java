package vn.needy.ecommerce.repository;

import java.util.List;

import vn.needy.ecommerce.domain.entity.Category;

public interface CategoryRepository {

	List<Category> getLinkCategories(String preCategory);

	List<Category> getCompanyLinkCategories(long companyId, String preCategory);
	
}
