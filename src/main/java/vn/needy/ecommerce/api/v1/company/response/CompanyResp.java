package vn.needy.ecommerce.api.v1.company.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;

public class CompanyResp extends BaseResponse {

	private static final long serialVersionUID = 130272848L;
	
	private CompanyWrapper company;
	
	public CompanyResp() {
		super();
	}

	public CompanyResp(CompanyWrapper companyWrapper) {
		this.company = companyWrapper;
	}

	
	public CompanyWrapper getCompany() {
		return company;
	}

	public void setCompany(CompanyWrapper company) {
		this.company = company;
	}
}
