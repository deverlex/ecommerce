package vn.needy.ecommerce.api.v1.attribute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.needy.ecommerce.api.v1.attribute.json.AttrsJson;
import vn.needy.ecommerce.api.v1.attribute.response.ListAttrsResponse;
import vn.needy.ecommerce.repository.AttributeRepository;

import java.util.LinkedList;
import java.util.List;

@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    AttributeRepository attrsRepository;

    @Override
    public ListAttrsResponse getListAttrsOfCategory(String category) {
        List<AttrsJson> attrsJsons = new LinkedList<>();
        List<String> attrs = attrsRepository.getListAttributeCategory(category);
        for(String attr : attrs) {
            attrsJsons.add(new AttrsJson(attr));
        }
        ListAttrsResponse response = new ListAttrsResponse();
        response.setAttributes(attrsJsons);
        return response;
    }
}
