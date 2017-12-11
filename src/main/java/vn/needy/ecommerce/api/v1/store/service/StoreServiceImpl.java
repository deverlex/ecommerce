package vn.needy.ecommerce.api.v1.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.mysql.Store;
import vn.needy.ecommerce.api.v1.store.response.StoreResponse;
import vn.needy.ecommerce.repository.StoreRepository;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepository;
	
	@Override
	public StoreResponse getStoreInformation(long userId) {
		Store store = storeRepository.getStore(userId);
		if (store != null) {

		}
		return new StoreResponse();
	}

}
