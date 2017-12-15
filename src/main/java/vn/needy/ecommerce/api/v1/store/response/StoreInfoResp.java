package vn.needy.ecommerce.api.v1.store.response;

import vn.needy.ecommerce.model.wrapper.StoreWrapper;

public class StoreInfoResp {
    private StoreWrapper store;

    public StoreInfoResp(StoreWrapper store) {
        this.store = store;
    }

    public StoreWrapper getStore() {
        return store;
    }

    public void setStore(StoreWrapper store) {
        this.store = store;
    }
}
