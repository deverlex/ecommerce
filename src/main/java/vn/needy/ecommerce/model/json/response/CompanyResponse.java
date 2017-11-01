package vn.needy.ecommerce.model.json.response;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.entity.CompanyJson;

public class CompanyResponse extends BaseResponse {

	private static final long serialVersionUID = 130272848L;
	
	private CompanyJson company;
	
	public CompanyResponse() {
		super();
	}

	public CompanyResponse(CompanyJson companyJson) {
		this.company = companyJson;
	}
	
	public CompanyJson getCompany() {
		return company;
	}

	public void setCompany(CompanyJson company) {
		this.company = company;
	}
	
}
