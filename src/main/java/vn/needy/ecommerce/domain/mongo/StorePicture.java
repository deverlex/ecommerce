package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.BaseFile;

@Document(collection = "store_picture")
public class StorePicture implements Serializable {

	private static final long serialVersionUID = 1747223008771L;

	@Id
	private String id;
	@Field(value = "store_id")
	private long storeId;
	private BaseFile image;
	
	public StorePicture() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public BaseFile getImage() {
		return image;
	}

	public void setImage(BaseFile image) {
		this.image = image;
	}
	
}
