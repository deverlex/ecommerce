package vn.needy.ecommerce.service;

import vn.needy.ecommerce.model.json.response.CompanyResponse;

public interface CompanyService {
	
	CompanyResponse findCompanyInherent(long userId);
}
