package vn.needy.ecommerce.api.v1.company.service;

import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.ecommerce.api.v1.company.response.CompanyResponse;

public interface CompanyService {
	
	CompanyResponse findCompanyInformation(long userId);
	
	CompanyResponse registerCompany(long userId, RegisterCompanyRequest registerCompanyRequest);
}
