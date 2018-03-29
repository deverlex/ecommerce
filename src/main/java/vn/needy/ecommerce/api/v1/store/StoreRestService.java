package vn.needy.ecommerce.api.v1.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.needy.ecommerce.api.base.RequestWrapper;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.store.request.UpdateStoreInfoReq;
import vn.needy.ecommerce.api.v1.store.service.StoreService;
import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.security.IdentificationUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StoreRestService {

	@Autowired
	private StoreService mStoreService;

	@Autowired
	private IdentificationUtils idUtils;

	@RequestMapping(value = "${needy.route.v1.stores.find_our_store}", method = RequestMethod.GET)
	// v1/companies/users
	public ResponseEntity<ResponseWrapper> findOurStore(HttpServletRequest request) {
		long userId = idUtils.getIdentification(request);

		return null;
	}

	@RequestMapping(value = "${needy.route.v1.stores.information_details}", method = RequestMethod.GET)
	//v1/stores/{store_id}/infomations/details
	public ResponseEntity<ResponseWrapper> getStoreInformations(HttpServletRequest request,
			@PathVariable(value = "store_id") String storeId) {
		long id = CipherID.decrypt(storeId);
		Long userId = idUtils.getIdentification(request);
		ResponseWrapper response = mStoreService.getStoreInformation(userId);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "${needy.route.v1.stores.information_details}", method = RequestMethod.PUT)
	// v1/stores/{store_id}/infomations/details
	public ResponseEntity<ResponseWrapper> updateStoreInformations(
			HttpServletRequest request,
			@PathVariable(value = "store_id") String storeId,
			@RequestBody RequestWrapper<UpdateStoreInfoReq> infoReq) {
		System.out.println(infoReq.getData().getName());
		long stId = CipherID.decrypt(storeId);
		long userId = idUtils.getIdentification(request);
		ResponseWrapper response = mStoreService.updateStoreInformation(userId, stId, infoReq.getData());
		return ResponseEntity.ok(response);
	}
}
