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
	public ResponseWrapper getStoreInformation(long userId) {
		Map map = storeRepository.getStoreInformation(userId);
		Store store = (Store) map.get("store");
		int totalStaff = (int) map.get("totalStaff");
		if (store != null) {
			StoreWrapper storeWrapper = new StoreWrapper(store);
			ResponseWrapper<StoreInfoResp> response = new ResponseWrapper<>(BaseStatus.OK, BaseCode.OK, "");
			response.setData(new StoreInfoResp(storeWrapper, totalStaff));
			return response;
		}

		return new ResponseWrapper(BaseStatus.ERROR, BaseCode.BAD_REQUEST, "Failed.");
	}

	@Override
	public ResponseWrapper updateStoreInformation(long userId, long storeId, UpdateStoreInfoReq storeInfoReq) {
		boolean isUpdate = storeRepository.updateStoreInformation(userId, storeId, storeInfoReq);
		if (isUpdate) {
			return new ResponseWrapper(BaseStatus.OK, BaseCode.OK, "Done");
		} else {
			return new ResponseWrapper(BaseStatus.ERROR, BaseCode.BAD_REQUEST, "Failed");
		}
	}

}
