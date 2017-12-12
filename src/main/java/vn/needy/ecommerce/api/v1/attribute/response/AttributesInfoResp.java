package vn.needy.ecommerce.api.v1.attribute.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.AttributeValueWrapper;
import vn.needy.ecommerce.model.wrapper.AttributeWrapper;

import java.util.List;
import java.util.Map;

public class AttributesInfoResp extends BaseResponse {
    private static final long serialVersionUID = 13242799836L;

    private List<AttributeValueWrapper> attributes;

    public AttributesInfoResp() {
        super();
    }

    public List<AttributeValueWrapper> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeValueWrapper> attributes) {
        this.attributes = attributes;
    }
}
