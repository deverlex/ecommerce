package vn.needy.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.entity.Store;
import vn.needy.ecommerce.domain.mongo.StoreDetail;
import vn.needy.ecommerce.model.json.response.StoreResponse;
import vn.needy.ecommerce.repository.StoreResponsitory;
import vn.needy.ecommerce.service.StoreService;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreResponsitory storeResponsitory;
	
	@Override
	public StoreResponse getStoreInformation(long userId) {
		Store store = storeResponsitory.getStore(userId);
		if (store != null) {
			StoreDetail storeDetail = storeResponsitory.getStoreDetail(store.getId());
			StoreResponse response = new StoreResponse();
			
		}
		return new StoreResponse();
	}

}
