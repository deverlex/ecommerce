package vn.needy.ecommerce.api.base;

import vn.needy.ecommerce.model.base.BaseModel;

public class BaseResponse extends BaseModel {

	private static final long serialVersionUID = 1487362522L;
	
	protected boolean success;
	protected String message;

	public BaseResponse() {
		super();
		this.success = true;
		this.message = "Success";
	}
	
	public BaseResponse(String message) {
		this.message = message;
	}
	
	public BaseResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
