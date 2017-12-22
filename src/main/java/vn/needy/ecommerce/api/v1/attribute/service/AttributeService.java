package vn.needy.ecommerce.api.v1.attribute.service;

import vn.needy.ecommerce.api.base.ResponseWrapper;

public interface AttributeService {

    ResponseWrapper getListAttributeOfCategory(String category);
}
