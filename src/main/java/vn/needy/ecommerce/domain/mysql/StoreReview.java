package vn.needy.ecommerce.domain.mysql;

import vn.needy.ecommerce.domain.BaseDomain;

import java.util.Date;

public class StoreReview extends BaseDomain {

    private static final long serialVersionUID = 15543452723L;

    private long id;
    private long userId;
    private long storeId;
    private short rating;
    private String review;
    private Date lastUpdatedTime;

    public StoreReview() {
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

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
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
