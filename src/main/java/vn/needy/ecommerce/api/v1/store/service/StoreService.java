package vn.needy.ecommerce.api.v1.store.service;

import vn.needy.ecommerce.api.v1.store.response.StoreResponse;

public interface StoreService {
	
	StoreResponse getStoreInformation(long userId);
}
