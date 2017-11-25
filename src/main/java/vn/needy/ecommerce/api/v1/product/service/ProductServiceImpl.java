package vn.needy.ecommerce.api.v1.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.entity.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;
import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.api.v1.product.request.AddProductRequest;
import vn.needy.ecommerce.repository.ProductRepository;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public BaseResponse addProduct(long userId, long companyId, AddProductRequest addProductRequest) {
		/// Product se kiem duyet boi quan tri vien
		Product product = new Product();
		product.setCategory(addProductRequest.getCategory());
		product.setCompanyId(companyId);
		product.setState(1);
		product.setLastUpdatedBy(userId);
		
		ProductDetail productDetail = new ProductDetail();
		productDetail.setCategoryId(addProductRequest.getCategory());
		productDetail.setAttribute(addProductRequest.getAttributes());
		
		
		return null;
	}

}
