package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.Store;

public interface StoreResponsitory {

	public long registerStore(Store store);
	
	public int getLastStoreCode(long companyId);
}
