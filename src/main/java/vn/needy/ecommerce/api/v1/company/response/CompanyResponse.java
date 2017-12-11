package vn.needy.ecommerce.api.v1.company.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.domain.entity.FeeTransport;
import vn.needy.ecommerce.model.json.CompanyJson;
import vn.needy.ecommerce.model.json.FeeTransportJson;

import java.util.List;

public class CompanyResponse extends BaseResponse {

	private static final long serialVersionUID = 130272848L;
	
	private CompanyJson company;

	private int staffCount;

	private List<FeeTransportJson> feeTransports;
	
	public CompanyResponse() {
		super();
	}

	public CompanyResponse(CompanyJson companyJson) {
		this.company = companyJson;
	}

	public CompanyResponse(CompanyJson companyJson, int staffCount) {
		this.company = companyJson;
		this.staffCount = staffCount;
	}

	public CompanyResponse(CompanyJson companyJson, int staffCount, List<FeeTransportJson> feeTransports) {
		this.company = companyJson;
		this.staffCount = staffCount;
		this.feeTransports = feeTransports;
	}
	
	public CompanyJson getCompany() {
		return company;
	}

	public void setCompany(CompanyJson company) {
		this.company = company;
	}

	public int getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(int staffCount) {
		this.staffCount = staffCount;
	}

	public List<FeeTransportJson> getFeeTransports() {
		return feeTransports;
	}

	public void setFeeTransports(List<FeeTransportJson> feeTransports) {
		this.feeTransports = feeTransports;
	}
}
