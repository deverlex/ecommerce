package vn.needy.ecommerce.model.wrapper;

import java.io.Serializable;
import java.util.List;

public class AttributeValueWrapper implements Serializable {

    private String name;
    private String title;
    private int dataType;
    private List<Object> values;

    public AttributeValueWrapper(AttributeWrapper attribute, List<Object> values) {
        this.name = attribute.getName();
        this.title = attribute.getTitle();
        this.dataType = attribute.getDataType();
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}
