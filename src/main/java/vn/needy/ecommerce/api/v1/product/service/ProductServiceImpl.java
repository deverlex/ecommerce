package vn.needy.ecommerce.api.v1.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.api.v1.product.request.AddProductPlReq;
import vn.needy.ecommerce.api.v1.product.respone.ProductPnInfoResp;
import vn.needy.ecommerce.domain.mysql.Product;
import vn.needy.ecommerce.domain.mongo.ProductDetail;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductPnReq;
import vn.needy.ecommerce.model.wrapper.LinkProductWrapper;
import vn.needy.ecommerce.model.wrapper.ProductStoreWrapper;
import vn.needy.ecommerce.model.wrapper.ProductWrapper;
import vn.needy.ecommerce.repository.FeeTransportRepository;
import vn.needy.ecommerce.repository.LinkProductRepository;
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

	@Autowired
	LinkProductRepository mLinkProductRepository;
	
	@Override
	@Transactional
	public ResponseWrapper addProductPn(long userId, long storeId, long companyId, AddProductPnReq addProductPnReq) {
		/// Product se kiem duyet boi quan tri vien

		Product product = new Product();
		product.setCategory(addProductPnReq.getCategory());
		product.setCompanyId(companyId);
		product.setState(1);
		product.setPrice(addProductPnReq.getPrice());
		product.setName(addProductPnReq.getName());
		product.setLastUpdatedBy(userId);

		ProductDetail productDetail = new ProductDetail();
		productDetail.setCategoryId(addProductPnReq.getCategory());
		productDetail.setAttribute(addProductPnReq.getAttributes());
		productDetail.setStoreId(storeId);
		productDetail.setName(addProductPnReq.getName());
		productDetail.setDescription(addProductPnReq.getDescription());

		long productId = productRepository.addProductPn(userId, storeId, companyId, product, productDetail);

		mFeeTransportRepository.updateFeeTransport(productId, userId, addProductPnReq.getFeeTransport());

		ProductStoreWrapper productStoreWrapper = new ProductStoreWrapper();
		productStoreWrapper.setProductId(productId);
		productStoreWrapper.setStoreId(storeId);
		productStoreWrapper.setInventory(addProductPnReq.getQuantity());
		mProductStoreRepository.add(userId, productStoreWrapper);
		return new ResponseWrapper(BaseStatus.OK, BaseCode.OK, "");
	}

	@Override
	@Transactional
	public ResponseWrapper addProductPl(long userId, long storeId, long companyId, AddProductPlReq addProductPlReq) {
		Product product = new Product();
		product.setCategory(addProductPlReq.getCategory());
		product.setCompanyId(companyId);
		product.setState(1);
		product.setName(addProductPlReq.getName());
		product.setLastUpdatedBy(userId);

		ProductDetail productDetail = new ProductDetail();
		productDetail.setCategoryId(addProductPlReq.getCategory());
		productDetail.setStoreId(storeId);
		productDetail.setName(addProductPlReq.getName());
		productDetail.setDescription(addProductPlReq.getDescription());

		long productId = productRepository.addProductPl(userId, storeId, companyId, product, productDetail);

		mFeeTransportRepository.updateFeeTransport(productId, userId, addProductPlReq.getFeeTransport());

		ProductStoreWrapper productStoreWrapper = new ProductStoreWrapper();
		productStoreWrapper.setProductId(productId);
		productStoreWrapper.setStoreId(storeId);
		productStoreWrapper.setInventory(0);
		mProductStoreRepository.add(userId, productStoreWrapper);
		List<LinkProductWrapper> linkProductWrappers = new LinkedList<>();
		for (long pId : addProductPlReq.getProducts()) {
			LinkProductWrapper wrapper = new LinkProductWrapper();
			wrapper.setProductId(pId);
			wrapper.setReferenceId(productId);
			linkProductWrappers.add(wrapper);
		}
		mLinkProductRepository.add(userId, linkProductWrappers);
		return new ResponseWrapper(BaseStatus.OK, BaseCode.OK, "");
	}

	@Override
	public ResponseWrapper getProductsOfCompanyByCategory(long userId, long companyId, String category) {
		List<Product> products = productRepository.getProductsOfCompanyByCategory(userId, companyId, category);
		List<ProductWrapper> productWrappers = new LinkedList<>();
		for (Product p : products) {
			productWrappers.add(new ProductWrapper(p));
		}
		return new ResponseWrapper<ProductPnInfoResp>(BaseStatus.OK, BaseCode.OK, null).setData(new ProductPnInfoResp(productWrappers));
	}

}
