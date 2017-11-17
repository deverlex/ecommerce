package vn.needy.ecommerce.service;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.request.AddProductRequest;

public interface ProductService {
	
	BaseResponse addProduct(long userId, long companyId, AddProductRequest addProductRequest);
}
