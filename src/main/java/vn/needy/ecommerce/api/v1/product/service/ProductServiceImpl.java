package vn.needy.ecommerce.api.v1.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.domain.mysql.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductReq;
import vn.needy.ecommerce.model.wrapper.ProductStoreWrapper;
import vn.needy.ecommerce.repository.FeeTransportRepository;
import vn.needy.ecommerce.repository.ProductRepository;
import vn.needy.ecommerce.repository.ProductStoreRepository;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	FeeTransportRepository mFeeTransportRepository;

	@Autowired
	ProductStoreRepository mProductStoreRepository;
	
	@Override
	public ResponseWrapper addProduct(long userId, long storeId, long companyId, AddProductReq addProductReq) {
		/// Product se kiem duyet boi quan tri vien

		System.out.println(userId + "," + storeId + "," + companyId);

		Product product = new Product();
		product.setCategory(addProductReq.getCategory());
		product.setCompanyId(companyId);
		product.setState(1);
		product.setPrice(addProductReq.getPrice());
		product.setName(addProductReq.getName());
		product.setLastUpdatedBy(userId);

		ProductDetail productDetail = new ProductDetail();
		productDetail.setCategoryId(addProductReq.getCategory());
		productDetail.setAttribute(addProductReq.getAttributes());
		productDetail.setStoreId(storeId);
		productDetail.setName(addProductReq.getName());
		productDetail.setDescription(addProductReq.getDescription());

		long productId = productRepository.addProduct(userId, storeId, companyId, product, productDetail);
		mFeeTransportRepository.updateFeeTransport(productId, userId, addProductReq.getFeeTransport());

		ProductStoreWrapper productStoreWrapper = new ProductStoreWrapper();
		productStoreWrapper.setProductId(productId);
		productStoreWrapper.setStoreId(storeId);
		productStoreWrapper.setInventory(addProductReq.getQuantity());
		mProductStoreRepository.add(userId, productStoreWrapper);
		return new ResponseWrapper(BaseStatus.OK, BaseCode.OK, "");
	}

}
