package vn.needy.ecommerce.api.v1.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.store.request.UpdateStoreInfoReq;
import vn.needy.ecommerce.api.v1.store.response.StoreInfoResp;
import vn.needy.ecommerce.domain.mysql.Store;
import vn.needy.ecommerce.model.wrapper.StoreWrapper;
import vn.needy.ecommerce.repository.StoreRepository;

import java.util.Map;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepository;

	@Override
	public ResponseWrapper getOurStore(long userId, long companyId) {

		return null;
	}

	@Override
	public ResponseWrapper getStoreInformation(long storeId) {
		Map map = storeRepository.getStoreInformation(storeId);
		Store store = (Store) map.get("store");
		if (store != null) {
			StoreWrapper storeWrapper = new StoreWrapper(store);
			ResponseWrapper<StoreInfoResp> response = new ResponseWrapper<>(BaseStatus.OK, BaseCode.OK, "");
			response.setData(new StoreInfoResp(storeWrapper));
			return response;
		}

		return new ResponseWrapper(BaseStatus.ERROR, BaseCode.BAD_REQUEST, "Failed.");
	}

	@Override
	public ResponseWrapper updateStoreInformation(long storeId, UpdateStoreInfoReq storeInfoReq) {
		return null;
	}

}
