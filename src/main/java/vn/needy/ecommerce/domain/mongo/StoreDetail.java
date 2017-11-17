package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.BaseFile;

@Document(collection = "store_detail")
public class StoreDetail implements Serializable {

	private static final long serialVersionUID = 89876654461L;
	
	@Id
	private long storeId;
	private String description;
	private BaseFile avatar;
	@Field(value = "cover_picture")
	private List<String> coverPicture;
	
	public StoreDetail() {
		super();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BaseFile getAvatar() {
		return avatar;
	}

	public void setAvatar(BaseFile avatar) {
		this.avatar = avatar;
	}

	public List<String> getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(List<String> coverPicture) {
		this.coverPicture = coverPicture;
	}
	
}
