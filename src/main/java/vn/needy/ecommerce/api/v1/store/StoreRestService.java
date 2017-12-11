package vn.needy.ecommerce.api.v1.store;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.api.v1.store.response.StoreResponse;

@RestController
public class StoreRestService {

	@RequestMapping(value = "${needy.route.v1.stores.infomation}")
	public ResponseEntity<StoreResponse> getStoreInformation() {
		
		return null;
	}
}
