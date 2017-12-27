package vn.needy.ecommerce.api.v1.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.api.v1.product.respone.ProductPnInfoResp;
import vn.needy.ecommerce.domain.mysql.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductReq;
import vn.needy.ecommerce.model.wrapper.ProductStoreWrapper;
import vn.needy.ecommerce.model.wrapper.ProductWrapper;
import vn.needy.ecommerce.repository.FeeTransportRepository;
import vn.needy.ecommerce.repository.ProductRepository;
import vn.needy.ecommerce.repository.ProductStoreRepository;

import java.util.LinkedList;
import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	FeeTransportRepository mFeeTransportRepository;

	@Autowired
	ProductStoreRepository mProductStoreRepository;
	
	@Override
	@Transactional
	public ResponseWrapper addProduct(long userId, long storeId, long companyId, AddProductReq addProductReq) {
		/// Product se kiem duyet boi quan tri vien

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

	@Override
	public ResponseWrapper getAllProductOfCompany(long userId, long companyId) {
		List<Product> products = productRepository.getAllProductOfCompany(userId, companyId);
		List<ProductWrapper> productWrappers = new LinkedList<>();
		for (Product p : products) {
			productWrappers.add(new ProductWrapper(p));
		}
		return new ResponseWrapper<ProductPnInfoResp>(BaseStatus.OK, BaseCode.OK, null).setData(new ProductPnInfoResp(productWrappers));
	}

}
