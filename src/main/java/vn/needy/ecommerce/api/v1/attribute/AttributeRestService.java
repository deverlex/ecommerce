package vn.needy.ecommerce.api.v1.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.api.v1.attribute.response.ListAttrsResponse;
import vn.needy.ecommerce.api.v1.attribute.service.AttributeService;

@RestController
public class AttributeRestService {

    @Autowired
    AttributeService attributeService;

    @RequestMapping(value = "${needy.route.v1.attributes.lists}")
    public ResponseEntity<?> getAttributeCategory(
            @RequestParam(value = "category_name") String category) {
        ListAttrsResponse response = attributeService.getListAttrsOfCategory(category);
        return ResponseEntity.ok(response);
    }
}
