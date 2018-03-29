package vn.needy.ecommerce.model.enums;

public enum CompanyReputationState {
	
	ACTIVE(1), INACTIVE(-1), WAITING(0);
	
	private int state;

	private CompanyReputationState(int state) {
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
