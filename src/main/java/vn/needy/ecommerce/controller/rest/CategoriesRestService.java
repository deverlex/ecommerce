package vn.needy.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.model.json.response.CategoriesResponse;
import vn.needy.ecommerce.service.CategoriesService;

@RestController
public class CategoriesRestService {

	@Autowired
	CategoriesService categoriesService;
	
	@RequestMapping(value = "${needy.route.categories.lists}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCategories() {
		CategoriesResponse response = categoriesService.getProductCategories();
		return ResponseEntity.ok(response);
	}
}
