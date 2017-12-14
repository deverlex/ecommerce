package vn.needy.ecommerce.api.v1.store.response;

import vn.needy.ecommerce.model.wrapper.StoreWrapper;

import java.io.Serializable;

public class StoreResp implements Serializable {

	private static final long serialVersionUID = 199836200123L;

	private StoreWrapper store;
	
	public StoreResp() {
		super();
	}
	
	public StoreResp(StoreWrapper storeWrapper) {
		store = storeWrapper;
	}

	public StoreWrapper getStore() {
		return store;
	}

	public void setStore(StoreWrapper store) {
		this.store = store;
	}
	
}
