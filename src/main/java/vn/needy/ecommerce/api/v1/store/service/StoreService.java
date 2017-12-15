package vn.needy.ecommerce.api.v1.store.service;

import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.store.request.UpdateStoreInfoReq;

public interface StoreService {

	ResponseWrapper getOurStore(long userId, long companyId);

	ResponseWrapper getStoreInformation(long userId);

	ResponseWrapper updateStoreInformation(long storeId, UpdateStoreInfoReq storeInfoReq);
}
