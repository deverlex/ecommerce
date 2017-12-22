package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mongo.AttributeValue;

import java.util.List;

public interface AttributeValueRepository {

    List<AttributeValue> getListAttributeValue(String category, List<String> attributes);
}
