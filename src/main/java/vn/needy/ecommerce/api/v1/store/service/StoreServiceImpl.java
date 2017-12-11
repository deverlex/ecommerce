package vn.needy.ecommerce.api.v1.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.store.request.UpdateStoreInfoReq;
import vn.needy.ecommerce.repository.StoreRepository;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepository;

	@Override
	public BaseResponse getOurStore(long userId, long companyId) {

		return null;
	}

	@Override
	public BaseResponse getStoreInformation(long userId) {

		return null;
	}

	@Override
	public BaseResponse updateStoreInformation(long storeId, UpdateStoreInfoReq storeInfoReq) {
		return null;
	}

}
