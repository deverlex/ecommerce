package vn.needy.ecommerce.model.enums;

public enum UserState {
	DELETED(-2), LOCKED(-1), INACTIVE(0), ACTIVE(1);

	private int state;

	private UserState(int state) {
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
