package vn.needy.ecommerce.model.json;

import vn.needy.ecommerce.domain.mysql.Attribute;

import java.io.Serializable;

public class AttrsJson implements Serializable {

    private static final long serialVersionUID = 1532998899646L;

    private String name;
    private String title;
    private int dataType;

    public AttrsJson() {
        super();
    }

    public AttrsJson(Attribute attribute) {
        this.name = attribute.getName();
        this.dataType = attribute.getDataType();
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
}
