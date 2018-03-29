package vn.needy.ecommerce.model.enums;

public enum PayBehavior {
	
	INITIALIZE(0),
	PAYONBANK(1), // thanh toan chuyen khoan ngan hang
	PAYONCASH(2), // thanh toan tien mat 
	CHARGE(3), // thu phi tu dong
	REFUND(4); // hoan tien
	
	private int behavior;
	
	private PayBehavior(final int behavior) {
		this.behavior = behavior;
	}
	
	public int getBehavior() {
		return this.behavior;
	}
	
	@Override
	public String toString() {
		return String.valueOf(behavior);
	}

	public String getName() {
		return this.getName();
	}
}
