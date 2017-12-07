package vn.needy.ecommerce.api.v1.attribute.json;

import java.io.Serializable;

public class AttrsJson implements Serializable {

    private static final long serialVersionUID = 1532998899646L;

    private String name;
    private String title;

    public AttrsJson() {
        super();
    }

    public AttrsJson(String category) {
        this.name = category;
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
}
