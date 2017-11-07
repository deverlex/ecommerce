package vn.needy.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.common.utils.TextUtils;
import vn.needy.ecommerce.model.json.response.CategoriesResponse;
import vn.needy.ecommerce.service.CategoriesService;

@RestController
public class CategoriesRestService {
	
	@Autowired
	CategoriesService categoriesService;
	
	// v1/p/categories  - Get all categories
	// v1/p/categories?company_id=  - Get all categories of company 
	@RequestMapping(value = "${needy.route.categories.price_now.lists}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductCategories(@RequestParam(value = "company_id") String companyId) {
		CategoriesResponse response = null;
		if (TextUtils.isEmpty(companyId)) {
			// Get all categories
			response = categoriesService.getProductCategories();
		} else {
			// Get categories of company
			long id = CipherID.decrypt(companyId);
			response = categoriesService.getCompanyProductCategory(id);
		}
		 
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "${needy.route.categories.price_now.sublists}", method = RequestMethod.GET)
	// v1/p/categories/{category}
	// v1/p/categories/{category}?company_id=
	public ResponseEntity<?> getProductSubCategories(
			@PathVariable(value = "category", required = true) String category,
			@RequestParam(value = "company_id") String companyId) {
		CategoriesResponse response = null;
		if (TextUtils.isEmpty(companyId)) {
			// Get all subCategories
			response = categoriesService.getProductSubCategory(category);
		} else {
			// Get subCategories of company
			long id = CipherID.decrypt(companyId);
			response = categoriesService.getCompanyProductSubCategory(id, category);
		}
		return ResponseEntity.ok(response);
	}
	
}
