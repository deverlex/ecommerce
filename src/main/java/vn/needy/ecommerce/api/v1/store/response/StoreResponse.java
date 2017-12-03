package vn.needy.ecommerce.api.v1.store.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.json.StoreJson;

public class StoreResponse extends BaseResponse {

	private static final long serialVersionUID = 199836200123L;

	private StoreJson store;
	
	public StoreResponse() {
		super();
	}
	
	public StoreResponse(StoreJson storeJson) {
		store = storeJson;
	}

	public StoreJson getStore() {
		return store;
	}

	public void setStore(StoreJson store) {
		this.store = store;
	}
	
}