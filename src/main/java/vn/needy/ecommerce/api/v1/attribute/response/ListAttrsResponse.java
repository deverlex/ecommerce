package vn.needy.ecommerce.api.v1.attribute.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.attribute.json.AttrsJson;

import java.util.List;

public class ListAttrsResponse extends BaseResponse {
    private static final long serialVersionUID = 13242799836L;

    private List<AttrsJson> attributes;

    public ListAttrsResponse() {
        super();
    }

    public List<AttrsJson> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttrsJson> attributes) {
        this.attributes = attributes;
    }
}
