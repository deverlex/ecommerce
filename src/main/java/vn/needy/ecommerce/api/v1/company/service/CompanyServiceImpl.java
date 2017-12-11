package vn.needy.ecommerce.api.v1.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.base.ResponseCode;
import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoRequest;
import vn.needy.ecommerce.api.v1.company.response.CompanyInfoResp;
import vn.needy.ecommerce.common.utils.TimeProvider;
import vn.needy.ecommerce.domain.mysql.*;
import vn.needy.ecommerce.model.enums.StaffState;
import vn.needy.ecommerce.model.enums.StaffStatus;
import vn.needy.ecommerce.model.enums.StoreState;
import vn.needy.ecommerce.model.enums.StoreStatus;
import vn.needy.ecommerce.model.enums.CompanyState;
import vn.needy.ecommerce.model.enums.PayBehavior;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;
import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.ecommerce.api.v1.company.response.CompanyResp;
import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;
import vn.needy.ecommerce.repository.BudgetRepository;
import vn.needy.ecommerce.repository.CompanyRepository;
import vn.needy.ecommerce.repository.CompanyGuaranteeRepository;
import vn.needy.ecommerce.repository.CompanyStaffRepository;
import vn.needy.ecommerce.repository.PayRepository;
import vn.needy.ecommerce.repository.StoreRepository;
import vn.needy.ecommerce.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("companiesService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    CompanyRepository companiesRepository;

    @Autowired
    StoreRepository storesRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    PayRepository paysRepository;

    @Autowired
    CompanyGuaranteeRepository companyReputationRepository;

    @Autowired
    CompanyStaffRepository companyStaffRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public BaseResponse findOurCompany(long userId) {
        Company company = companiesRepository.findByUserId(userId);
        if (company != null) {
            return new CompanyResp(new CompanyWrapper(company));
        }
        return new BaseResponse(BaseResponse.ERROR, ResponseCode.NO_CONTENT);
    }

    // This function is not used.
    @Override
    public BaseResponse findCompanyInformation(long userId) {
        Company company = companiesRepository.findByUserId(userId);
        if (company != null) {
            boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
            CompanyWrapper companyWrapper = new CompanyWrapper(company);
            companyWrapper.setReputation(isCompanyReputation);
            return new CompanyResp(companyWrapper);
        }
        return new CompanyResp();
    }

    @Override
    public BaseResponse findInformation(long userId) {
        Map companyInfo = companiesRepository.findInformationByUserId(userId);
        if (companyInfo != null) {
            Company company = (Company) companyInfo.get("company");
            int staffCount = (int) companyInfo.get("staffCount");
            List<FeeTransportWrapper> feeTransportWrappers = new ArrayList<>();
            for (FeeTransport ft : (List<FeeTransport>) companyInfo.get("feeTransport")) {
                feeTransportWrappers.add(new FeeTransportWrapper(ft));
            }
            boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
            CompanyWrapper companyWrapper = new CompanyWrapper(company);
            companyWrapper.setReputation(isCompanyReputation);
            return new CompanyInfoResp(companyWrapper, staffCount, feeTransportWrappers);
        } else {
            return new BaseResponse(BaseResponse.ERROR, ResponseCode.ERROR);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BaseResponse registerCompany(long userId, RegisterCompanyRequest registerCompanyRequest) {
        CompanyResp companyResp = new CompanyResp();
        Company findCompany = companiesRepository.findByUserId(userId);

        if (findCompany != null) {
            BaseResponse response = new BaseResponse(BaseResponse.ERROR,
                    ResponseCode.NOT_IMPLEMENTED, "You can not register 2 company both active.");
            return response;
        }

        // insert into Companies tables
        Company registerCompany = new Company();
        registerCompany.setLastUpdatedBy(userId);
        registerCompany.setName(registerCompanyRequest.getCompanyName());
        registerCompany.setAddress(registerCompanyRequest.getOfficeAddress());
        registerCompany.setLevel(0);
        registerCompany.setState(CompanyState.ACTIVE.getState());
        long companyId = companiesRepository.registerCompany(registerCompany);

        // insert into Budgets
        // MODIFY UPDATE
        Budget registerBudget = new Budget();
        registerBudget.setCompanyId(companyId);
        registerBudget.setBudget(500000f);
        long budgetId = budgetRepository.createBudget(registerBudget);

        // insert into PayLogs
        Pay payLog = new Pay();
        payLog.setBudgetId(budgetId);
        payLog.setBehavior(PayBehavior.INITIALIZE.getBehavior());
        payLog.setBudgetCharge(500000f);
        payLog.setDescription("Initialize budget account.");
        payLog.setCreatedBy(1); // system id
        paysRepository.createPay(payLog);

        // insert into Stores
        Store registerStore = new Store();
        registerStore.setCompanyId(companyId);
        registerStore.setState(StoreState.INACTIVE.getState());
        registerStore.setStatus(StoreStatus.CLOSED_TIME.getStatus());
        registerStore.setName(registerCompanyRequest.getStoreName());
        registerStore.setAddress(registerCompanyRequest.getStoreAddress());
        registerStore.setOpeningTime(timeProvider.parseTime("08:00:00"));
        registerStore.setClosingTime(timeProvider.parseTime("20:00:00"));
        registerStore.setLat(registerCompanyRequest.getLat());
        registerStore.setLng(registerCompanyRequest.getLng());
        registerStore.setLastUpdatedBy(userId);
        long storeId = storesRepository.registerStore(registerStore);

        // insert into CompanyStaff
        CompanyStaff staff = new CompanyStaff();
        staff.setUserId(userId);
        staff.setLastUpdatedBy(userId);
        staff.setCompanyId(companyId);
        staff.setStoreId(storeId);
        staff.setFcmToken(registerCompanyRequest.getFcmToken());
        staff.setState(StaffState.ACTIVED.getState());
        staff.setStatus(StaffStatus.READY.getStatus());
        companyStaffRepository.insertCompanyStaff(staff);

        userRoleRepository.registerUserListRole(userId, Role.List.CompanyOwner, userId);

        Company company = companiesRepository.findByUserId(userId);
        if (company != null) {
            boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
            CompanyWrapper companyWrapper = new CompanyWrapper(company);
            companyWrapper.setReputation(isCompanyReputation);
            return new CompanyResp(companyWrapper);
        }

        return new BaseResponse(BaseResponse.ERROR,
                ResponseCode.NOT_IMPLEMENTED, "Create company is failed.");
    }

    @Override
    public BaseResponse updateCompanyInformation(long companyId, long userId, UpdateCompanyInfoRequest infoRequest) {
        boolean isUpdate = companiesRepository.updateCompanyInformation(companyId, userId, infoRequest);
        if (isUpdate) {
            return new BaseResponse();
        } else {
            return new BaseResponse(BaseResponse.ERROR, ResponseCode.ERROR);
        }
    }


}
