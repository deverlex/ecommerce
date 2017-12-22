package vn.needy.ecommerce.api.v1.company;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.needy.ecommerce.api.base.RequestWrapper;
import vn.needy.ecommerce.api.base.ResponseWrapper;
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
	public ResponseEntity<ResponseWrapper> findOurCompany(HttpServletRequest request) {
		long userId = idUtils.getIdentification(request);
		// get and return company
		return ResponseEntity.ok(companyService.findOurCompany(userId));
	}
	
	@RequestMapping(value= "${needy.route.v1.companies.information_details}", method = RequestMethod.GET)
	public ResponseEntity<ResponseWrapper> findCompanyInformation(HttpServletRequest request) {
		Long userId = idUtils.getIdentification(request);
		ResponseWrapper response = companyService.findInformation(userId);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value= "${needy.route.v1.companies.registers}", method = RequestMethod.POST)
	public ResponseEntity<ResponseWrapper> registerCompany(HttpServletRequest request,
														   @RequestBody RequestWrapper<RegisterCompanyReq> registerCompanyReq) {
		Long userId = idUtils.getIdentification(request);
		ResponseWrapper response = companyService.registerCompany(userId, registerCompanyReq.getData());
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "${needy.route.v1.companies.update_information_details}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseWrapper> updateCompanyInformation(HttpServletRequest request, @PathVariable(value = "company_id") String compantId,
                                                                    @RequestBody RequestWrapper<UpdateCompanyInfoReq> infoRequest) {
		long companyId = CipherID.decrypt(compantId);
		long userId = idUtils.getIdentification(request);
		ResponseWrapper response = companyService.updateCompanyInformation(companyId, userId, infoRequest.getData());
		return ResponseEntity.ok(response);
	}
}
