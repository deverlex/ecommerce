package vn.needy.ecommerce.api.v1.product;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductReq;
import vn.needy.ecommerce.common.service.StorageService;

@RestController
public class ProductRestService {
	
	@Autowired
	StorageService storageService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAllProductOfCompany(
			) {
		
		return null;
	}
	
	
	@RequestMapping(value = "${needy.route.v1.products.price_now.add_new}")
	public ResponseEntity<?> addProductPriceNowOfCompany(
			@RequestParam(value = "company_id", required = true) String companyId,
			@RequestParam(value = "store_id", required = true) String storeId,
			@RequestBody AddProductReq addProductReq) {
		System.out.println(Calendar.getInstance().getTimeInMillis());
		long cId = CipherID.decrypt(companyId);
		long sId = CipherID.decrypt(storeId);
		System.out.println(Calendar.getInstance().getTimeInMillis());
		System.out.println(addProductReq.getCategory());
		return null;
	}
	
	@RequestMapping(value = "${needy.route.v1.products.price_now.images.add_new}", method = RequestMethod.POST)
	public ResponseEntity<?> addImageForProduct(
			@PathVariable(value = "product_id") String productId,
			@RequestParam(value = "multiparty") MultipartFile image) {
		System.out.println("product_id? " + productId);
		System.out.println(image.getOriginalFilename());
		
		System.out.println(storageService.storeImage(image));
		return ResponseEntity.ok(new ResponseWrapper());
	}
	
	// productId is id of productCompany
	@RequestMapping(value = "${needy.route.v1.products.price_now.images.update}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateImageForProduct(
			@PathVariable(value = "product_id") String productId,
			@RequestParam(value = "multiparty") MultipartFile image) {
		System.out.println("product_id? " + productId);
		
		return ResponseEntity.ok(new ResponseWrapper());
	}
}
