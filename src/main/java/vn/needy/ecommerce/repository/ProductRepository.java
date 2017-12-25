package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;

public interface ProductRepository {
	
	long addProduct(long userId, long storeId, long companyId, Product product, ProductDetail productDetail);
}
