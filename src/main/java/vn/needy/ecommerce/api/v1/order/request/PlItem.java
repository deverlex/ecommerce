package vn.needy.ecommerce.api.v1.order.request;

import java.io.Serializable;
import java.util.List;

public class PlItem implements Serializable {

    private static final long serialVersionUID = 143560908244L;

    private String productId;
    private String description;
    /// images description will upload

    public PlItem() {
        super();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
