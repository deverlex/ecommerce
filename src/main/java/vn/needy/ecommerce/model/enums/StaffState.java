package vn.needy.ecommerce.model.enums;

public enum StaffState {
	INACTIVE(0),
	ACTIVED(1);
	
	private int state;
	
	private StaffState(final int state) {
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
