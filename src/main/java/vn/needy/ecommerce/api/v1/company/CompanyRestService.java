package vn.needy.ecommerce.api.v1.company;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyReq;
import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoReq;
import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.api.v1.company.service.CompanyService;

@RestController
public class CompanyRestService {
	
	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "${needy.route.v1.companies.find_our_company}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findOurCompany(HttpServletRequest request) {
		long userId = idUtils.getIdentification(request);
		// get and return company
		return ResponseEntity.ok(companyService.findOurCompany(userId));
	}
	
	@RequestMapping(value= "${needy.route.v1.companies.information_details}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findCompanyInformation(HttpServletRequest request) {
		Long userId = idUtils.getIdentification(request);
		BaseResponse response = companyService.findInformation(userId);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value= "${needy.route.v1.companies.registers}", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> registerCompany(HttpServletRequest request,
			@RequestBody RegisterCompanyReq registerCompanyReq) {
		Long userId = idUtils.getIdentification(request);
		BaseResponse response = companyService.registerCompany(userId, registerCompanyReq);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "${needy.route.v1.companies.update_information_details}", method = RequestMethod.PUT)
	public ResponseEntity<BaseResponse> updateCompanyInformation(HttpServletRequest request, @PathVariable(value = "company_id") String compantId,
																 @RequestBody UpdateCompanyInfoReq infoRequest) {
		long companyId = CipherID.decrypt(compantId);
		long userId = idUtils.getIdentification(request);
		BaseResponse response = companyService.updateCompanyInformation(companyId, userId, infoRequest);
		return ResponseEntity.ok(response);
	}
}
