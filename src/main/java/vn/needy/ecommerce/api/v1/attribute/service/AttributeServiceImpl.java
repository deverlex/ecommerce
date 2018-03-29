package vn.needy.ecommerce.api.v1.attribute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.domain.mongo.AttributeValue;
import vn.needy.ecommerce.model.wrapper.AttributeValueWrapper;
import vn.needy.ecommerce.model.wrapper.AttributeWrapper;
import vn.needy.ecommerce.api.v1.attribute.response.AttributesInfoResp;
import vn.needy.ecommerce.domain.mysql.Attribute;
import vn.needy.ecommerce.repository.AttributeRepository;
import vn.needy.ecommerce.repository.AttributeValueRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("attributeService")
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    AttributeRepository attributesRepo;

    @Autowired
    AttributeValueRepository attributeValueRepo;

    @Override
    public ResponseWrapper getListAttributeOfCategory(String category) {
        List<Attribute> attributes = attributesRepo.getListAttributeCategory(category);

        List<AttributeValue> attributeValues = attributeValueRepo
                .getListAttributeValue(category, getListAttributeName(attributes));

        // convert list attribute values to map - for create a response
        Map<String, List<Object>> values = attributeValuesToMap(attributeValues);

        List<AttributeValueWrapper> contentResp = new LinkedList<>();
        for (Attribute attribute : attributes) {
            // wrapper value
            contentResp.add(new AttributeValueWrapper(
                    new AttributeWrapper(attribute),
                    values.get(attribute.getName())
            ));
        }

        return new ResponseWrapper<AttributesInfoResp>(BaseStatus.OK, BaseCode.OK, "")
                .setData(new AttributesInfoResp(contentResp));
    }

    private Map<String, List<Object>> attributeValuesToMap(List<AttributeValue> list) {
        Map<String, List<Object>> map = new HashMap<>();
        for (AttributeValue value : list) {
            map.put(value.getAttribute(), value.getValues());
        }
        return map;
    }

    private List<String> getListAttributeName(List<Attribute> attributes) {
        List<String> names = new LinkedList<>();
        for (Attribute attribute : attributes) {
            names.add(attribute.getName());
        }
        return names;
    }
}
