package vn.needy.ecommerce.api.v1.company.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UpdateCompanyInfoReq implements Serializable {
    private String name;
    private String address;
    private String description;
    private String siteURL;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date foundedDate;
    @JsonFormat(pattern = "HH:mm")
    private Date openingTime;
    @JsonFormat(pattern = "HH:mm")
    private Date closingTime;
    private float lat;
    private float lng;
    private List<FeeTransportReq> feeTransport;

    public UpdateCompanyInfoReq() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public List<FeeTransportReq> getFeeTransport() {
        return feeTransport;
    }

    public void setFeeTransport(List<FeeTransportReq> feeTransport) {
        this.feeTransport = feeTransport;
    }
}
