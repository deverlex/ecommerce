package vn.needy.ecommerce.api.v1.attribute.service;

import vn.needy.ecommerce.api.base.BaseResponse;

public interface AttributeService {

    BaseResponse getListAttributeOfCategory(String category);
}
