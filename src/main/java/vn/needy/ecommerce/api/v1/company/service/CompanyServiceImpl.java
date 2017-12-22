package vn.needy.ecommerce.api.v1.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.needy.ecommerce.api.base.BaseCode;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.base.BaseStatus;
import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoReq;
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
import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyReq;
import vn.needy.ecommerce.api.v1.company.response.CompanyResp;
import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;
import vn.needy.ecommerce.repository.*;

import java.util.LinkedList;
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

    @Autowired
    FeeTransportRepository feeTransportRepo;

    @Override
    public ResponseWrapper findOurCompany(long userId) {
        Company company = companiesRepository.findOurByUserId(userId);
        if (company != null) {
            return new ResponseWrapper<CompanyResp>(BaseStatus.OK, BaseCode.OK, "").setData(new CompanyResp(new CompanyWrapper(company)));
        }
        return new ResponseWrapper(BaseStatus.ERROR, BaseCode.NOT_FOUND, "Not found");
    }

    // This function is not used.
    @Override
    public ResponseWrapper findCompanyInformation(long userId) {
        Company company = companiesRepository.findOurByUserId(userId);
        if (company != null) {
            boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
            CompanyWrapper companyWrapper = new CompanyWrapper(company);
            companyWrapper.setReputation(isCompanyReputation);
            return new ResponseWrapper<CompanyResp>(BaseStatus.OK, BaseCode.OK, "").setData(new CompanyResp(companyWrapper));
        }
        return new ResponseWrapper(BaseStatus.ERROR, BaseCode.NOT_FOUND, "Not found");
    }

    @Override
    public ResponseWrapper findInformation(long userId) {
        Map companyInfo = companiesRepository.findInformationByUserId(userId);
        if (companyInfo != null) {
            Company company = (Company) companyInfo.get("company");
            int staffCount = (int) companyInfo.get("staffCount");

            List<FeeTransport> feeTransports = feeTransportRepo.getListByCompanyId(company.getId());

            List<FeeTransportWrapper> feeTransportWrappers = new LinkedList<>();
            for (FeeTransport ft : feeTransports) {
                feeTransportWrappers.add(new FeeTransportWrapper(ft));
            }

            boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
            CompanyWrapper companyWrapper = new CompanyWrapper(company);
            companyWrapper.setReputation(isCompanyReputation);

            return new ResponseWrapper<CompanyInfoResp>(BaseStatus.OK, BaseCode.OK, "")
                    .setData(new CompanyInfoResp(companyWrapper, staffCount, feeTransportWrappers));
        } else {
            return new ResponseWrapper(BaseStatus.ERROR, BaseCode.NOT_FOUND, "Not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseWrapper registerCompany(long userId, RegisterCompanyReq registerCompanyReq) {
        CompanyResp companyResp = new CompanyResp();
        Company findCompany = companiesRepository.findOurByUserId(userId);

        if (findCompany != null) {
            return new ResponseWrapper(BaseStatus.ERROR, BaseCode.CONFLICT, "You can not register 2 company both active.");
        }

        // insert into Companies tables
        Company registerCompany = new Company();
        registerCompany.setLastUpdatedBy(userId);
        registerCompany.setName(registerCompanyReq.getCompanyName());
        registerCompany.setAddress(registerCompanyReq.getOfficeAddress());
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
        registerStore.setName(registerCompanyReq.getStoreName());
        registerStore.setAddress(registerCompanyReq.getStoreAddress());
        registerStore.setOpeningTime(timeProvider.parseTime("08:00:00"));
        registerStore.setClosingTime(timeProvider.parseTime("20:00:00"));
        registerStore.setLat(registerCompanyReq.getLat());
        registerStore.setLng(registerCompanyReq.getLng());
        registerStore.setLastUpdatedBy(userId);
        long storeId = storesRepository.registerStore(registerStore);

        // insert into CompanyStaff
        CompanyStaff staff = new CompanyStaff();
        staff.setUserId(userId);
        staff.setLastUpdatedBy(userId);
        staff.setCompanyId(companyId);
        staff.setStoreId(storeId);
        staff.setFcmToken(registerCompanyReq.getFcmToken());
        staff.setState(StaffState.ACTIVED.getState());
        staff.setStatus(StaffStatus.READY.getStatus());
        companyStaffRepository.insertCompanyStaff(staff);

        userRoleRepository.registerUserListRole(userId, Role.List.CompanyOwner, userId);

        Company company = companiesRepository.findOurByUserId(userId);
        if (company != null) {
            boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
            CompanyWrapper companyWrapper = new CompanyWrapper(company);
            companyWrapper.setReputation(isCompanyReputation);
            return new ResponseWrapper<CompanyResp>(BaseStatus.OK, BaseCode.OK, "")
                    .setData(new CompanyResp(companyWrapper));
        }

        return new ResponseWrapper(BaseStatus.ERROR, BaseCode.BAD_REQUEST, "Create company is failed.");
    }

    @Override
    public ResponseWrapper updateCompanyInformation(long companyId, long userId, UpdateCompanyInfoReq infoRequest) {
        boolean isUpdate = companiesRepository.updateCompanyInformation(companyId, userId, infoRequest);
        feeTransportRepo.updateFeeTransport(companyId, userId, infoRequest.getFeeTransport());
        feeTransportRepo.removeFeeTransport(companyId, infoRequest.getRemoveFeeTransportId());
        if (isUpdate) {
            return new ResponseWrapper(BaseStatus.OK, BaseCode.OK, "Done");
        } else {
            return new ResponseWrapper(BaseStatus.ERROR, BaseCode.BAD_REQUEST, "Failed");
        }
    }


}
