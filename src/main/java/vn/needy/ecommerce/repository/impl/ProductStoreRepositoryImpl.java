package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.model.wrapper.ProductStoreWrapper;
import vn.needy.ecommerce.repository.ProductStoreRepository;


@Repository("productStoreRepository")
public class ProductStoreRepositoryImpl implements ProductStoreRepository{
    @Autowired
    JdbcTemplate jdbc;


    @Override
    public boolean add(long userId, ProductStoreWrapper productStoreWrapper) {
        return jdbc.update("insert into product_store (product_id, store_id, inventory, last_updated_by) values (?, ?, ?, ?)",
                productStoreWrapper.getProductId(),
                productStoreWrapper.getStoreId(),
                productStoreWrapper.getInventory(),
                userId) == 1;
    }
}
