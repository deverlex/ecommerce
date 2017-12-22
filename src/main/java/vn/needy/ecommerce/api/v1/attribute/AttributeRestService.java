package vn.needy.ecommerce.api.v1.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.attribute.service.AttributeService;

@RestController
public class AttributeRestService {

    @Autowired
    AttributeService attributeService;

    @RequestMapping(value = "${needy.route.v1.attributes.get_lists}", method = RequestMethod.GET)
    public ResponseEntity<?> getAttributeCategory(
            @RequestParam(value = "category_name") String category) {
        ResponseWrapper response = attributeService.getListAttributeOfCategory(category);
        return ResponseEntity.ok(response);
    }
}
