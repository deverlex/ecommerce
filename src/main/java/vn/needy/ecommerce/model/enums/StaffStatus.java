package vn.needy.ecommerce.model.enums;

public enum StaffStatus {
	
	READY(0),
	BUSY(1);
	
	private int status;
	
	private StaffStatus(final int status) {
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
