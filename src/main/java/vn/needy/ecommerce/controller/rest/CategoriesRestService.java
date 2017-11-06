package vn.needy.ecommerce.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.model.json.response.CategoriesResponse;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.service.CategoriesService;

@RestController
public class CategoriesRestService {

	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	CategoriesService categoriesService;
	
	// this using for buyer & add new product
	// categories/products
	@RequestMapping(value = "${needy.route.categories.products.lists}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductCategories() {
		CategoriesResponse response = categoriesService.getProductCategories();
		return ResponseEntity.ok(response);
	}
	
	// expand above
	@RequestMapping(value = "${needy.route.categories.products.sublists}", method = RequestMethod.GET)
	// categories/{category}/products
	public ResponseEntity<?> getProductSubCategories(
			@PathVariable(value = "category", required = true) String category) {
		CategoriesResponse response = categoriesService.getProductSubCategory(category);
		return ResponseEntity.ok(response);
	}
	
	// this using for provider manage they products
	@RequestMapping(value = "${needy.route.categories.products.companies.lists}", method = RequestMethod.GET)
	// categories/products/companies?id=?
	public ResponseEntity<?> getCompanyProductCategories(HttpServletRequest request,
			@RequestParam(value = "company_id", required = true) String companyId) {
		long id = CipherID.decrypt(companyId);
		CategoriesResponse response = categoriesService.getCompanyProductCategory(id);
		return ResponseEntity.ok(response);
	}
	
	// expand above
	@RequestMapping(value = "${needy.route.categories.products.companies.sublists}")
	// categories/{category}/products/companies
	public ResponseEntity<?> getCompanyProductSubCategories(HttpServletRequest request,
			@RequestParam(value = "company_id", required = true) String companyId,
			@PathVariable(value = "category", required = true) String category) {
		long id = CipherID.decrypt(companyId);
		CategoriesResponse response = categoriesService.getCompanyProductSubCategory(id, category);
		return ResponseEntity.ok(response);
	}
}
