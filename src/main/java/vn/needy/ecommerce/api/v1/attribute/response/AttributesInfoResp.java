package vn.needy.ecommerce.api.v1.attribute.response;

import vn.needy.ecommerce.model.wrapper.AttributeValueWrapper;

import java.io.Serializable;
import java.util.List;

public class AttributesInfoResp implements Serializable {
    private static final long serialVersionUID = 13242799836L;

    private List<AttributeValueWrapper> attributes;

    public AttributesInfoResp() {
        super();
    }

    public AttributesInfoResp(List<AttributeValueWrapper> attributes) {
        this.attributes = attributes;
    }

    public List<AttributeValueWrapper> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeValueWrapper> attributes) {
        this.attributes = attributes;
    }
}
