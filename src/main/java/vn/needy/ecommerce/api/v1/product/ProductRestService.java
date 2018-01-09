package vn.needy.ecommerce.api.v1.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.api.base.RequestWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductPlReq;
import vn.needy.ecommerce.api.v1.product.service.ProductService;
import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.product.request.AddProductPnReq;
import vn.needy.ecommerce.common.service.StorageService;
import vn.needy.ecommerce.security.IdentificationUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ProductRestService {

    @Autowired
    StorageService storageService;

    @Autowired
    ProductService mProductService;

    @Autowired
    private IdentificationUtils idUtils;

    @RequestMapping(value = "${needy.route.v1.products.price_now.get_products}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductOfCompany(
            HttpServletRequest request,
            @RequestParam(value = "company_id") String companyId,
            @RequestParam(value = "category") String category) {

        long cId = CipherID.decrypt(companyId);
        long userId = idUtils.getIdentification(request);

        ResponseWrapper responseWrapper = mProductService.getProductsOfCompanyByCategory(userId, cId, category);
        return ResponseEntity.ok(responseWrapper);
    }

    @RequestMapping(value = "${needy.route.v1.products.price_now.add_new}")
    public ResponseEntity<?> addProductPriceNowOfCompany(
            HttpServletRequest request,
            @RequestParam(value = "product_type") String productType,
            @RequestParam(value = "company_id") String companyId,
            @RequestParam(value = "store_id") String storeId,
            @RequestBody RequestWrapper<JsonNode> addProductReq) {

        ObjectMapper objectMapper = new ObjectMapper();
        long cId = CipherID.decrypt(companyId);
        long sId = CipherID.decrypt(storeId);
        long userId = idUtils.getIdentification(request);

        if (productType.equals("price_now")) {
            try {
                AddProductPnReq addProductPnReq = objectMapper.readValue(addProductReq.getData().toString(), AddProductPnReq.class);
                ResponseWrapper responseWrapper = mProductService.addProductPn(userId, sId, cId, addProductPnReq);
                return ResponseEntity.ok(responseWrapper);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (productType.equals("price_later")) {
            try {
                AddProductPlReq addProductPlReq = objectMapper.readValue(addProductReq.getData().toString(), AddProductPlReq.class);
                ResponseWrapper responseWrapper = mProductService.addProductPl(userId, sId, cId, addProductPlReq);
                return ResponseEntity.ok(responseWrapper);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        return ResponseEntity.ok(new ResponseWrapper<>(BaseStatus.ERROR, BaseCode.BAD_REQUEST, null));
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
