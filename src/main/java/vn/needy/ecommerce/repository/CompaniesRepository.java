package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.Company;

public interface CompaniesRepository {
	
	Company findCompanyDependencyByUserId(long userId);
	
	long registerCompany(Company company);
}
