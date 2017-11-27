package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.base.BaseDomain;

import java.util.Date;

public class ProductReview extends BaseDomain {

    private static final long serialVersionUID = 1552723L;

    private long id;
    private long userId;
    private long productId;
    private short rating;
    private String review;
    private Date lastUpdatedTime;

    public ProductReview() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
