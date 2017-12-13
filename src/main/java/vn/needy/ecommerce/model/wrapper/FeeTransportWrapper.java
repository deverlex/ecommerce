package vn.needy.ecommerce.model.wrapper;


import vn.needy.ecommerce.domain.mysql.FeeTransport;

import java.io.Serializable;
import java.util.Date;

public class FeeTransportWrapper implements Serializable {

    private long id;
    private short feeType;
    private float from;
    private float to;
    private float fee;

    public FeeTransportWrapper() {
        super();
    }

    public FeeTransportWrapper(FeeTransport feeTransport) {
        id = feeTransport.getId();
        feeType = feeTransport.getFeeType();
        from = feeTransport.getFrom();
        to = feeTransport.getTo();
        fee = feeTransport.getFee();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
