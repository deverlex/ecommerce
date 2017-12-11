package vn.needy.ecommerce.api.v1.store.service;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.store.request.UpdateStoreInfoReq;

public interface StoreService {

	BaseResponse getOurStore(long userId, long companyId);

	BaseResponse getStoreInformation(long storeId);

	BaseResponse updateStoreInformation(long storeId, UpdateStoreInfoReq storeInfoReq);
}
