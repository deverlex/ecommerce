package vn.needy.ecommerce.repository;

import java.util.List;

import vn.needy.ecommerce.domain.entity.Store;
import vn.needy.ecommerce.domain.mongo.StoreDetail;
import vn.needy.ecommerce.domain.mongo.StorePicture;

public interface StoreResponsitory {

	public long registerStore(Store store);
	
	public Store getStore(long userId);
	
	public StoreDetail getStoreDetail(long storeId);
	
	public List<StorePicture> getStorePictures(long storeId);
}
