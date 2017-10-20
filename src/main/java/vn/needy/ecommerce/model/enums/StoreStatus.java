package vn.needy.ecommerce.model.enums;

public enum StoreStatus {
	/*Da mo cua va san sang giao*/
	READY(1), 
	/*Ban*/
	BUSY(0),
	/*Dong cua*/
	CLOSED_TIME(-1);
	
	private int status;
	
	private StoreStatus(final int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	@Override
	public String toString() {
		return String.valueOf(status);
	}

	public String getName() {
		return this.getName();
	}
}
