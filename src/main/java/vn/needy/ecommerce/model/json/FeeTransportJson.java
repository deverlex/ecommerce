package vn.needy.ecommerce.model.json;

import vn.needy.ecommerce.domain.entity.FeeTransport;

import java.io.Serializable;
import java.util.Date;

public class FeeTransportJson implements Serializable {

    private short feeType;
    private float from;
    private float to;
    private float fee;
    private Date lastUpdatedTime;
    private long lastUpdatedBy;

    public FeeTransportJson() {
        super();
    }

    public FeeTransportJson(FeeTransport feeTransport) {
        feeType = feeTransport.getFeeType();
        from = feeTransport.getFrom();
        to = feeTransport.getTo();
        fee = feeTransport.getFee();
        lastUpdatedTime = feeTransport.getLastUpdatedTime();
        lastUpdatedBy = feeTransport.getLastUpdatedBy();
    }

    public short getFeeType() {
        return feeType;
    }

    public void setFeeType(short feeType) {
        this.feeType = feeType;
    }

    public float getFrom() {
        return from;
    }

    public void setFrom(float from) {
        this.from = from;
    }

    public float getTo() {
        return to;
    }

    public void setTo(float to) {
        this.to = to;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
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
