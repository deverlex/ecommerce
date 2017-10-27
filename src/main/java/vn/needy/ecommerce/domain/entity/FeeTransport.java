package vn.needy.ecommerce.domain.entity;

import vn.needy.ecommerce.domain.BaseDomain;

public class FeeTransport extends BaseDomain {

	private static final long serialVersionUID = 1545343535L;
	
	public static final String TABLE = "FeeTransport";
	
	private long id;
	private long companyId;
	private float distanceFrom;
	private float distanceTo;
	private float fee;
	
	public FeeTransport() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public float getDistanceFrom() {
		return distanceFrom;
	}

	public void setDistanceFrom(float distanceFrom) {
		this.distanceFrom = distanceFrom;
	}

	public float getDistanceTo() {
		return distanceTo;
	}

	public void setDistanceTo(float distanceTo) {
		this.distanceTo = distanceTo;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}
	
}
