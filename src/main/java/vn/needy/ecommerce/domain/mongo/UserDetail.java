package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.BaseFile;

@Document(collection = "userdetail")
public class UserDetail implements Serializable {
	
	private static final long serialVersionUID = 188944321316L;
	
	@Id
	private long id;
	@Indexed(unique = true)
	private String email;
	private Date birthday;
	private String gender;
	private BaseFile avatar;
	@Field("cover_picture")
	private BaseFile coverPicture;
	
	public UserDetail() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BaseFile getAvatar() {
		return avatar;
	}

	public void setAvatar(BaseFile avatar) {
		this.avatar = avatar;
	}

	public BaseFile getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(BaseFile coverPicture) {
		this.coverPicture = coverPicture;
	}
	
}
