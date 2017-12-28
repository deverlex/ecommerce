package vn.needy.ecommerce.model.wrapper;

import vn.needy.ecommerce.domain.mysql.LinkProduct;

import java.util.Date;

public class LinkProductWrapper {
    private long id;
    private long productId;
    private long referenceId;
    private Date lastUpdatedTime;
    private long lastUpdatedBy;

    public LinkProductWrapper() {

    }

    public LinkProductWrapper(LinkProduct linkProduct) {
        id = linkProduct.getId();
        productId = linkProduct.getProductId();
        referenceId = linkProduct.getReferenceId();
        lastUpdatedBy = linkProduct.getLastUpdatedBy();
        lastUpdatedTime = linkProduct.getLastUpdatedTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(long referenceId) {
        this.referenceId = referenceId;
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
