package vn.needy.ecommerce.api.v1.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.common.utils.TextUtils;
import vn.needy.ecommerce.api.v1.category.response.CategoriesResponse;
import vn.needy.ecommerce.api.v1.category.service.CategoriesService;

@RestController
public class CategoriesRestService {
	
	@Autowired
	CategoriesService categoriesService;
	
	// v1/pn/categories  - Get all categories
	// v1/pn/categories?company_id=  - Get all categories of company 
	@RequestMapping(value = "${needy.route.categories.price_now.lists}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategoriesPriceNow(
			@RequestParam(value = "company_id", required = false) String companyId) {
		CategoriesResponse response = null;
		if (TextUtils.isEmpty(companyId)) {
			// Get all categories
			response = categoriesService.getCategoriesPriceNow();
			
		} else {
			// Get categories of company
			long id = CipherID.decrypt(companyId);
			response = categoriesService.getCompanyCategoriesPriceNow(id);
		}
		 
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "${needy.route.categories.price_now.sublists}", method = RequestMethod.GET)
	// v1/pn/categories/{category}
	// v1/pn/categories/{category}?company_id=
	public ResponseEntity<?> getSubCategoriesPriceNow(
			@PathVariable(value = "category") String category,
			@RequestParam(value = "company_id", required = false) String companyId) {
		CategoriesResponse response = null;
		if (TextUtils.isEmpty(companyId)) {
			// Get all subCategories
			response = categoriesService.getSubCategoriesPriceNow(category);
		} else {
			// Get subCategories of company
			long id = CipherID.decrypt(companyId);
			response = categoriesService.getCompanySubCategoriesPriceNow(id, category);
		}
		return ResponseEntity.ok(response);
	}
	
}
