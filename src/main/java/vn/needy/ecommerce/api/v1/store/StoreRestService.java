package vn.needy.ecommerce.api.v1.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.security.IdentificationUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StoreRestService {

	@Autowired
	private IdentificationUtils idUtils;

	@RequestMapping(value = "${needy.route.v1.stores.find_our_store}", method = RequestMethod.GET)
	// v1/companies/users
	public ResponseEntity<BaseResponse> findOurStore(HttpServletRequest request) {
		long userId = idUtils.getIdentification(request);

		return null;
	}

	@RequestMapping(value = "${needy.route.v1.stores.information_details}", method = RequestMethod.GET)
	//v1/stores/{store_id}/infomations/details
	public ResponseEntity<BaseResponse> getStoreInformations(
			@PathVariable(value = "store_id") String storeId) {
		long id = CipherID.decrypt(storeId);

		return null;
	}

	@RequestMapping(value = "${needy.route.v1.stores.information_details}", method = RequestMethod.PUT)
	// v1/stores/{store_id}/infomations/details
	public ResponseEntity<BaseResponse> updateStoreInformations(
			@PathVariable(value = "store_id") String storeId) {
		long id = CipherID.decrypt(storeId);

		return null;
	}
}
