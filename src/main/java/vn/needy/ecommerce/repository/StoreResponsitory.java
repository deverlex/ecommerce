package vn.needy.ecommerce.repository;


import vn.needy.ecommerce.domain.entity.Store;
import vn.needy.ecommerce.domain.mongo.StoreDetail;

public interface StoreResponsitory {

	public long registerStore(Store store);
	
	public Store getStore(long userId);
	
	public StoreDetail getStoreDetail(long storeId);
	
//	public List<StorePicture> getStorePictures(long storeId);
}
