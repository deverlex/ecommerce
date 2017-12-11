package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.ecommerce.domain.mysql.Company;

import java.util.Map;

public interface CompanyRepository {
	
	Company findCompanyInformationByUserId(long userId);

	Map findInformationByUserId(long userId);

	long registerCompany(Company company);

	boolean updateCompanyInformation(long id, UpdateCompanyInfoRequest infoRequest);
}
