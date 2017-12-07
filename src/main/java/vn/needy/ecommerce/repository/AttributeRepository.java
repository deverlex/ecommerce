package vn.needy.ecommerce.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface AttributeRepository {

    List<String> getListAttributeCategory(String category);
}
