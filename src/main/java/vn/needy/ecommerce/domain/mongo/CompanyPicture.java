package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.BaseFile;

@Document(collection = "company_picture")
public class CompanyPicture implements Serializable {

	private static final long serialVersionUID = 19902231895L;
	
	@Id
	private String id;
	@Field(value = "company_id")
	private String companyId;
	@Field(value = "image")
	private BaseFile image;
	
	public CompanyPicture() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public BaseFile getImage() {
		return image;
	}

	public void setImage(BaseFile image) {
		this.image = image;
	}
	
}
