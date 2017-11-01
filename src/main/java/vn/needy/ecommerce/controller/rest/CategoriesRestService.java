package vn.needy.ecommerce.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesRestService {

	@RequestMapping(value = "${needy.route.categories.lists}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCategories() {
		
		return null;
	}
}
