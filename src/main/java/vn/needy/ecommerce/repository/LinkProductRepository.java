package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.model.wrapper.LinkProductWrapper;

import java.util.List;

public interface LinkProductRepository {
    void add(long userId, List<LinkProductWrapper> linkProductWrappers);
}
