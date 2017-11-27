package vn.needy.ecommerce.domain.mongo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import vn.needy.ecommerce.domain.base.BaseFile;

@Document(collection = "user_detail")
public class UserDetail implements Serializable {
	
	private static final long serialVersionUID = 188944321316L;
	
	@Id
	private long userId;
	@Indexed
	private String email;
	private Date birthday;
	private String gender;
	private BaseFile avatar;
	@Field("cover_picture")
	private BaseFile coverPicture;
	
	public UserDetail() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
