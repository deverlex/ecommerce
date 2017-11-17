package vn.needy.ecommerce.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.model.json.response.StoreResponse;

@RestController
public class StoreRestService {

	@RequestMapping(value = "${needy.route.stores.infomation}")
	public ResponseEntity<StoreResponse> getStoreInformation() {
		
		return null;
	}
}
