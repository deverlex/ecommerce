package vn.needy.ecommerce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.service.StorageService;

@RestController
public class ProductsRestService {
	
	@Autowired
	StorageService storageService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAllProductOfCompany(
			) {
		
		return null;
	}
	
	
	@RequestMapping(value = "${needy.route.products.price_now.add_new}")
	public ResponseEntity<?> addProductOfCompany(
			@RequestParam(value = "company_id", required = true) String companyId) {
		
		return null;
	}
	
	@RequestMapping(value = "${needy.route.products.price_now.images.add_new}", method = RequestMethod.POST)
	public ResponseEntity<?> addImageForProduct(
			@PathVariable(value = "product_id") String productId,
			@RequestParam(value = "multiparty") MultipartFile image) {
		System.out.println("product_id? " + productId);
		System.out.println(image.getOriginalFilename());
		
		System.out.println(storageService.storeImage(image));
		return ResponseEntity.ok(new BaseResponse());
	}
	
	// productId is id of productCompany
	@RequestMapping(value = "${needy.route.products.price_now.images.update}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateImageForProduct(
			@PathVariable(value = "product_id") String productId,
			@RequestParam(value = "multiparty") MultipartFile image) {
		System.out.println("product_id? " + productId);
		
		return ResponseEntity.ok(new BaseResponse());
	}
}
