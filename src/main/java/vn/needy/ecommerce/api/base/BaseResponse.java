package vn.needy.ecommerce.api.base;

import vn.needy.ecommerce.model.base.BaseModel;

public class BaseResponse extends BaseModel {

	private static final long serialVersionUID = 1487362522L;

	public static final String OK = "OK";
	public static final String ERROR = "ERROR";

	protected String status;
	protected int code;
	protected String message;

	public BaseResponse() {
		super();
		this.status = OK;
		this.code = ResponseCode.OK.getCode();
		this.message = "";
	}
	
	public BaseResponse(String status, ResponseCode code) {
		super();
		this.status = status;
		this.code = code.getCode();
	}

	public BaseResponse(String status, ResponseCode code, String message) {
		super();
		this.status = status;
		this.code = code.getCode();
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
