package vn.needy.ecommerce.api.v1.user.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.common.utils.CipherID;

public class BusinessIdResponse extends BaseResponse {

    private String companyId;
    private String storeId;

    public BusinessIdResponse(Long companyId, Long storeId) {
        super();
        this.companyId = CipherID.encrypt(companyId);
        this.storeId = CipherID.encrypt(storeId);
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
