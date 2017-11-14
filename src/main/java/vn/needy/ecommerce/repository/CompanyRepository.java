package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.Company;

public interface CompanyRepository {
	
	Company findCompanyInformationByUserId(long userId);
	
	long registerCompany(Company company);
}
