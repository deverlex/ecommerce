package vn.needy.ecommerce.api.v1.store.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.StoreWrapper;

public class StoreResponse extends BaseResponse {

	private static final long serialVersionUID = 199836200123L;

	private StoreWrapper store;
	
	public StoreResponse() {
		super();
	}
	
	public StoreResponse(StoreWrapper storeWrapper) {
		store = storeWrapper;
	}

	public StoreWrapper getStore() {
		return store;
	}

	public void setStore(StoreWrapper store) {
		this.store = store;
	}
	
}
