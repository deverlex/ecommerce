package vn.needy.ecommerce.repository;


import vn.needy.ecommerce.domain.entity.Store;

public interface StoreRepository {

	public long registerStore(Store store);
	
	public Store getStore(long userId);

//	public List<StorePicture> getStorePictures(long storeId);
}