package vn.needy.ecommerce.api.v1.attribute.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.AttributeWrapper;

import java.util.List;

public class ListAttrsResponse extends BaseResponse {
    private static final long serialVersionUID = 13242799836L;

    private List<AttributeWrapper> attributes;

    public ListAttrsResponse() {
        super();
    }

    public List<AttributeWrapper> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeWrapper> attributes) {
        this.attributes = attributes;
    }
}
