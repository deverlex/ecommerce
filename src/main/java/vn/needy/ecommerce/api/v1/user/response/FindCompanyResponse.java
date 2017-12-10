package vn.needy.ecommerce.api.v1.user.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.common.utils.CipherID;

public class FindCompanyResponse extends BaseResponse {

    private String id;

    public FindCompanyResponse(long id) {
        super();
        this.id = CipherID.encrypt(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
