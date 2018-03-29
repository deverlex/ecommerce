package vn.needy.ecommerce.repository;

import java.util.List;

public interface CategoryRepository {

	List<String> getLinkCategories(String preCategory);

	List<String> getCompanyLinkCategories(long companyId, String preCategory);

	List<String> getParentCategories(String preCategory);
}
