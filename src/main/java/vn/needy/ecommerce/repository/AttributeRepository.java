package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.Attribute;

import java.util.List;

public interface AttributeRepository {

    List<Attribute> getListAttributeCategory(String category);
}
