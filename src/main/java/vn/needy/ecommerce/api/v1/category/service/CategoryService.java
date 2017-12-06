package vn.needy.ecommerce.api.v1.category.service;

import vn.needy.ecommerce.api.v1.category.response.ListCategoryResponse;

public interface CategoryService {

	ListCategoryResponse getLinkCategories(String category);

	ListCategoryResponse getCompanyLinkCategories(long companyId, String category);
}
