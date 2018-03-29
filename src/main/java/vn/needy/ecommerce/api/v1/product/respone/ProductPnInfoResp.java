package vn.needy.ecommerce.api.v1.product.respone;

import vn.needy.ecommerce.model.wrapper.ProductWrapper;

import java.io.Serializable;
import java.util.List;

public class ProductPnInfoResp implements Serializable {
    private List<ProductWrapper> products;

    public ProductPnInfoResp(List<ProductWrapper> products) {
        this.products = products;
    }

    public List<ProductWrapper> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWrapper> products) {
        this.products = products;
    }
}
