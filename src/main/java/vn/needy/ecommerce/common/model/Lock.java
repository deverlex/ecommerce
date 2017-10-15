package vn.needy.ecommerce.common.model;

public class Lock {
	
	private boolean async;
	
	public Lock(boolean async) {
		this.async = async;
	}

	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}
	
}
