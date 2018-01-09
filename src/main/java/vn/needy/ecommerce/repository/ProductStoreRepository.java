package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.model.wrapper.ProductStoreWrapper;

public interface ProductStoreRepository {
    boolean add(long userId, ProductStoreWrapper productStoreWrapper);
}
