package vn.needy.ecommerce.api.v1.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.mysql.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductReq;
import vn.needy.ecommerce.repository.ProductRepository;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public ResponseWrapper addProduct(long userId, long companyId, AddProductReq addProductReq) {
		/// Product se kiem duyet boi quan tri vien
		Product product = new Product();
		product.setCategory(addProductReq.getCategory());
		product.setCompanyId(companyId);
		product.setState(1);
		product.setLastUpdatedBy(userId);
		
		ProductDetail productDetail = new ProductDetail();
		productDetail.setCategoryId(addProductReq.getCategory());
		productDetail.setAttribute(addProductReq.getAttributes());
		
		
		return null;
	}

}
