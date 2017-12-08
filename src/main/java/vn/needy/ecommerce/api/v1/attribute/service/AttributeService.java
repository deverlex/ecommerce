package vn.needy.ecommerce.api.v1.attribute.service;

import vn.needy.ecommerce.api.v1.attribute.response.ListAttrsResponse;

public interface AttributeService {

    ListAttrsResponse getListAttrsOfCategory(String category);
}
