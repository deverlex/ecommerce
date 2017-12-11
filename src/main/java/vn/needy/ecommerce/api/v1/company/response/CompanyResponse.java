package vn.needy.ecommerce.api.v1.company.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;

public class CompanyResponse extends BaseResponse {

	private static final long serialVersionUID = 130272848L;
	
	private CompanyWrapper company;
	
	public CompanyResponse() {
		super();
	}

	public CompanyResponse(CompanyWrapper companyWrapper) {
		this.company = companyWrapper;
	}
	
	public CompanyWrapper getCompany() {
		return company;
	}

	public void setCompany(CompanyWrapper company) {
		this.company = company;
	}
	
}
