package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.base.BaseDomain;

public class OrderProduct extends BaseDomain {

    private static final long serialVersionUID = 1434324647643L;

    private long id;
    private long orderId;
    private long productId;
    private short quantity;

    public OrderProduct() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
}
