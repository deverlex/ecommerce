package vn.needy.ecommerce.api.v1.user.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;
import vn.needy.ecommerce.model.wrapper.StoreWrapper;

public class BusinessInfoResp extends BaseResponse {

    private CompanyWrapper company;
    private StoreWrapper store;

    public BusinessInfoResp() {
        super();
    }

    public BusinessInfoResp(CompanyWrapper company, StoreWrapper store) {
        super();
        this.company = company;
        this.store = store;
    }

    public CompanyWrapper getCompany() {
        return company;
    }

    public void setCompany(CompanyWrapper company) {
        this.company = company;
    }

    public StoreWrapper getStore() {
        return store;
    }

    public void setStore(StoreWrapper store) {
        this.store = store;
    }
}
