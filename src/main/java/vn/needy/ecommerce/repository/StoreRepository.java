package vn.needy.ecommerce.repository;


import vn.needy.ecommerce.api.v1.store.request.UpdateStoreInfoReq;
import vn.needy.ecommerce.domain.mysql.Store;

import java.util.Map;

public interface StoreRepository {

	long registerStore(Store store);
	
	Store getOurByUserId(long userId);

	Map getStoreInformation(long storeId);

	boolean updateStoreInformation(long storeId, UpdateStoreInfoReq req);

}
