package vn.needy.ecommerce.api.v1.product.service;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.product.request.AddProductRequest;

public interface ProductService {
	
	BaseResponse addProduct(long userId, long companyId, AddProductRequest addProductRequest);
}
