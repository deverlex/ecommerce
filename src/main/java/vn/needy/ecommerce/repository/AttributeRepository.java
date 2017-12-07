package vn.needy.ecommerce.repository;

import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.domain.entity.Attribute;

import java.util.List;

public interface AttributeRepository {

    List<Attribute> getListAttributeCategory(String category);
}
