package vn.needy.ecommerce.api.v1.order.request;

import java.io.Serializable;

public class PnItem implements Serializable {

    private static final long serialVersionUID = 143563525244L;

    private String productId;
    private int quantity;

    public PnItem() {
        super();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
