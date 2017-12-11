package vn.needy.ecommerce.api.v1.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.common.Constance;
import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.common.utils.TextUtils;
import vn.needy.ecommerce.api.v1.category.response.ListCategoryResponse;
import vn.needy.ecommerce.api.v1.category.service.CategoryService;

@RestController
public class CategoryRestService {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "${needy.route.v1.categories.get_lists}", method = RequestMethod.GET)
	// v1/categories/{category}
	// v1/categories/{category}?company_id=
	public ResponseEntity<?> getLinkCategories(
			@PathVariable(value = "category") String category,
			@RequestParam(value = "company_id", required = false) String companyId) {
		BaseResponse response = null;
		if (TextUtils.isEmpty(companyId)) {
			// Get all link categories
			response = categoryService.getLinkCategories(category);
		} else {
			// Get link categories of company
			long id = CipherID.decrypt(companyId);
			response = categoryService.getCompanyLinkCategories(id, category);
		}
		return ResponseEntity.ok(response);
	}
	
}
