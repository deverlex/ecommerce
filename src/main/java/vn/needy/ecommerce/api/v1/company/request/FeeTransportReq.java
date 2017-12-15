package vn.needy.ecommerce.api.v1.company.request;

public class FeeTransportReq {
    private long id;
    private short feeType;
    private float from;
    private float to;
    private float fee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
