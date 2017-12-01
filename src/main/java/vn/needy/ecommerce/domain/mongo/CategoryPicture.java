package vn.needy.ecommerce.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import vn.needy.ecommerce.domain.base.BaseFile;

import java.io.Serializable;

@Document(collection = "category_picture")
public class CategoryPicture implements Serializable {

    private static final long serialVersionUID = 19902231895L;

    @Id
    private String id;
    private String category;
    private String resolution;
    @Field(value = "image")
    private BaseFile image;

    public CategoryPicture() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public BaseFile getImage() {
        return image;
    }

    public void setImage(BaseFile image) {
        this.image = image;
    }
}
