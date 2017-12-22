package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;

public interface ProductRepository {
	
	long addProduct(Product product, ProductDetail productDetail);
}
