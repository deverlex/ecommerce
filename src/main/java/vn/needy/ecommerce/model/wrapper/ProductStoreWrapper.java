package vn.needy.ecommerce.model.wrapper;

import vn.needy.ecommerce.domain.mysql.ProductStore;

import java.util.Date;

public class ProductStoreWrapper {
    private long productId;
    private long storeId;
    private int inventory;
    private Date lastUpdatedTime;
    private long lastUpdatedBy;

    public ProductStoreWrapper() {
    }

    public ProductStoreWrapper(ProductStore productStore) {
        productId = productStore.getProductId();
        storeId = productStore.getStoreId();
        inventory = productStore.getInventory();
        lastUpdatedBy = productStore.getLastUpdatedBy();
        lastUpdatedBy = productStore.getLastUpdatedBy();
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
