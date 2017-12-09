package vn.needy.ecommerce.api.v1.category.service;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.category.response.ListCategoryResponse;

public interface CategoryService {

	BaseResponse getLinkCategories(String category);

	BaseResponse getCompanyLinkCategories(long companyId, String category);
}
