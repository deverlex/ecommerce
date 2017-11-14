package vn.needy.ecommerce.service;

import vn.needy.ecommerce.model.json.request.RegisterCompanyRequest;
import vn.needy.ecommerce.model.json.response.CompanyResponse;

public interface CompaniesService {
	
	CompanyResponse findCompanyInformation(long userId);
	
	CompanyResponse registerCompany(long userId, RegisterCompanyRequest registerCompanyRequest);
}
