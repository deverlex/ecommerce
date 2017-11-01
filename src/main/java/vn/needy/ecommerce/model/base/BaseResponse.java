package vn.needy.ecommerce.model.base;

public class BaseResponse extends BaseModel {

	private static final long serialVersionUID = 1487362522L;
	
	protected int status;
	protected String message;

	public BaseResponse() {
		super();
		this.status = 200;
		this.message = "Success";
	}
	
	public BaseResponse(String message) {
		this.message = message;
	}
	
	public BaseResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
