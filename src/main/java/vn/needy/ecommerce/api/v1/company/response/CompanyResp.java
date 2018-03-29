package vn.needy.ecommerce.api.v1.company.response;

import vn.needy.ecommerce.model.wrapper.CompanyWrapper;

import java.io.Serializable;

public class CompanyResp implements Serializable {

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
