package vn.needy.ecommerce.api.v1.product.request;

import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;

import java.io.Serializable;
import java.util.List;

public class AddProductPlReq implements Serializable {
    private String category;
    private String name;
    private String promotion;
    private String description;
    private List<FeeTransportWrapper> feeTransport;
    private List<String> hashtag;
    private List<Long> products;

    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FeeTransportWrapper> getFeeTransport() {
        return feeTransport;
    }

    public void setFeeTransport(List<FeeTransportWrapper> feeTransport) {
        this.feeTransport = feeTransport;
    }

    public List<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(List<String> hashtag) {
        this.hashtag = hashtag;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }
}
