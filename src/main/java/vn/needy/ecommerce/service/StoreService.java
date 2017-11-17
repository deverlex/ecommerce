package vn.needy.ecommerce.service;

import vn.needy.ecommerce.model.json.response.StoreResponse;

public interface StoreService {
	
	StoreResponse getStoreInformation(long userId);
}
