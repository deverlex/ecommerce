package vn.needy.ecommerce.api.base;

import vn.needy.ecommerce.model.BaseModel;

import java.util.List;

public final class BaseResponse<T> extends BaseModel {

	private static final long serialVersionUID = 1487362522L;

	private String status;
	private int code;
	private T data;
	private String message;
	private List<String> links;

	public BaseResponse() {
		super();
		this.status = BaseStatus.ERROR.getStatus();
		this.code = BaseCode.BAD_REQUEST.getCode();
		this.message = "";
	}

	public BaseResponse(BaseStatus status, BaseCode code, String message) {
		this.status = status.getStatus();
		this.code = code.getCode();
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public BaseResponse<T> setStatus(BaseStatus status) {
		this.status = status.getStatus();
		return this;
	}

	public int getCode() {
		return code;
	}

	public BaseResponse<T> setCode(BaseCode code) {
		this.code = code.getCode();
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
