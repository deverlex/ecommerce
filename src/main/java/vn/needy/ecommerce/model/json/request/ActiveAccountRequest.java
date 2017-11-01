package vn.needy.ecommerce.model.json.request;

import java.io.Serializable;

public class ActiveAccountRequest implements Serializable {

	private static final long serialVersionUID = 1638594252534L;
	private String fullName;
	private String address;
	private float lat;
	private float lng;
	
	public ActiveAccountRequest() {
		super();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}
	
}
