package vn.needy.ecommerce.api.v1.category.service;

import vn.needy.ecommerce.api.base.ResponseWrapper;

public interface CategoryService {

	ResponseWrapper getLinkCategories(String category);

	ResponseWrapper getCompanyLinkCategories(long companyId, String category);
}
