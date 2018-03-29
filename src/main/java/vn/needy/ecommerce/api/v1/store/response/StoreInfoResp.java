package vn.needy.ecommerce.api.v1.store.response;

import vn.needy.ecommerce.model.wrapper.StoreWrapper;

public class StoreInfoResp {
    private StoreWrapper store;
    private int totalStaff;

    public StoreInfoResp(StoreWrapper store, int totalStaff) {
        this.store = store;
        this.totalStaff = totalStaff;
    }

    public StoreWrapper getStore() {
        return store;
    }

    public void setStore(StoreWrapper store) {
        this.store = store;
    }

    public int getTotalStaff() {
        return totalStaff;
    }

    public void setTotalStaff(int totalStaff) {
        this.totalStaff = totalStaff;
    }
}
