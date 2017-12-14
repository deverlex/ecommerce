package vn.needy.ecommerce.api.base;

import vn.needy.ecommerce.model.BaseModel;

import java.util.List;

public class BaseResponse<T> extends BaseModel {

	private static final long serialVersionUID = 1487362522L;

	protected boolean success;
	protected T data;
	protected String message;
	protected List<String> links;

	public BaseResponse() {
		super();
		this.success = false;
		this.message = "";
	}

	public BaseResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public BaseResponse<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public BaseResponse<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public BaseResponse<T> setData(T data) {
		this.data = data;
		return this;
	}

	public List<String> getLinks() {
		return links;
	}

	public BaseResponse<T> setLinks(List<String> links) {
		this.links = links;
		return this;
	}
}
