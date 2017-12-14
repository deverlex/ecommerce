package vn.needy.ecommerce.api.v1.company.service;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyReq;
import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoReq;

public interface CompanyService {

	BaseResponse findOurCompany(long userId);
	
	BaseResponse findCompanyInformation(long userId);

	BaseResponse findInformation(long userId);

	BaseResponse registerCompany(long userId, RegisterCompanyReq registerCompanyReq);

	BaseResponse updateCompanyInformation(long companyId, long userId, UpdateCompanyInfoReq infoRequest);
}
