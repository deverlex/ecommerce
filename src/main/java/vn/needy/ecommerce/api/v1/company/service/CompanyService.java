package vn.needy.ecommerce.api.v1.company.service;

import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyReq;
import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoReq;

public interface CompanyService {

	ResponseWrapper findOurCompany(long userId);
	
	ResponseWrapper findCompanyInformation(long userId);

	ResponseWrapper findInformation(long userId);

	ResponseWrapper registerCompany(long userId, RegisterCompanyReq registerCompanyReq);

	ResponseWrapper updateCompanyInformation(long companyId, long userId, UpdateCompanyInfoReq infoRequest);
}
