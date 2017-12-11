package vn.needy.ecommerce.api.v1.company.service;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoRequest;

public interface CompanyService {

	BaseResponse findOurCompany(long userId);
	
	BaseResponse findCompanyInformation(long userId);

	BaseResponse findInformation(long userId);

	BaseResponse registerCompany(long userId, RegisterCompanyRequest registerCompanyRequest);

	BaseResponse updateCompanyInformation(long id, UpdateCompanyInfoRequest infoRequest);
}
