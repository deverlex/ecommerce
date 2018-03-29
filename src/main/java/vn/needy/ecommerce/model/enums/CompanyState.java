package vn.needy.ecommerce.model.enums;

public enum CompanyState {
	
	CLOSED(-1),
	// Het tien
	INACTIVE(0),
	ACTIVE(1);
	
	private int state;
	
	private CompanyState(final int state) {
		this.state = state;
	}
	
	public int getState() {
		return this.state;
	}
	
	@Override
	public String toString() {
		return String.valueOf(state);
	}

	public String getName() {
		return this.getName();
	}
}
