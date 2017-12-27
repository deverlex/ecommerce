package vn.needy.ecommerce.api.v1.product.service;

import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductReq;

public interface ProductService {
	
	ResponseWrapper addProduct(long userId, long storeId, long companyId, AddProductReq addProductReq);

	ResponseWrapper getAllProductOfCompany(long userId, long companyId);
}
