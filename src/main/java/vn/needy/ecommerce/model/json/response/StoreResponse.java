package vn.needy.ecommerce.model.json.response;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.entity.StoreJson;

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
