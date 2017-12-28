package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.needy.ecommerce.model.wrapper.LinkProductWrapper;
import vn.needy.ecommerce.repository.LinkProductRepository;

import java.util.List;

@Repository("linkProductRepository")
public class LinkProductRepositoryImpl implements LinkProductRepository{
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public void add(long userId, List<LinkProductWrapper> linkProductWrappers) {
        for (LinkProductWrapper linkProductWrapper : linkProductWrappers) {
            jdbc.update("insert into link_product (product_id, reference_product, last_updated_by) values (?, ?, ?)",
                    linkProductWrapper.getProductId(),
                    linkProductWrapper.getReferenceId(),
                    userId);
        }
    }
}
