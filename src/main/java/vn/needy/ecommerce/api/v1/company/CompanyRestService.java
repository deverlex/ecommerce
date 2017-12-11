package vn.needy.ecommerce.api.v1.company;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.ecommerce.api.v1.company.response.CompanyResponse;
import vn.needy.ecommerce.common.utils.CipherID;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.api.v1.company.service.CompanyService;

@RestController
public class CompanyRestService {
	
	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value= "${needy.route.v1.companies.information_details}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findCompanyInformation(HttpServletRequest request) {
		Long userId = idUtils.getIdentification(request);
		BaseResponse response = companyService.findCompanyInformation(userId);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value= "${needy.route.v1.companies.registers}", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> registerCompany(HttpServletRequest request,
			@RequestBody RegisterCompanyRequest registerCompanyRequest) {
		Long userId = idUtils.getIdentification(request);
		BaseResponse response = companyService.registerCompany(userId, registerCompanyRequest);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "${needy.route.v1.companies.update_information_details}", method = RequestMethod.PUT)
	public ResponseEntity<BaseResponse> updateCompanyInformation(@PathVariable(value = "company_id") String compantId,
																 @RequestBody UpdateCompanyInfoRequest infoRequest) {
		long id = CipherID.decrypt(compantId);
		BaseResponse response = companyService.updateCompanyInformation(id, infoRequest);
		return ResponseEntity.ok(response);
	}
}
