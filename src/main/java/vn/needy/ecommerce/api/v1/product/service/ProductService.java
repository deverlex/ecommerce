package vn.needy.ecommerce.api.v1.product.service;

import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductPlReq;
import vn.needy.ecommerce.api.v1.product.request.AddProductPnReq;

public interface ProductService {
	
	ResponseWrapper addProductPn(long userId, long storeId, long companyId, AddProductPnReq addProductPnReq);
	ResponseWrapper addProductPl(long userId, long storeId, long companyId, AddProductPlReq addProductPlReq);

	ResponseWrapper getProductsOfCompanyByCategory(long userId, long companyId, String category);
}
