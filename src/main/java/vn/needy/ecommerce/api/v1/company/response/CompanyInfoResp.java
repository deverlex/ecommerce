package vn.needy.ecommerce.api.v1.company.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;

import java.util.List;

public class CompanyInfoResp extends BaseResponse {

    private CompanyWrapper company;
    private int staffCount;
    private List<FeeTransportWrapper> feeTransports;

    public CompanyInfoResp(CompanyWrapper wrapper, int staffCount) {
        this.company = wrapper;
        this.staffCount = staffCount;
    }

    public CompanyInfoResp(CompanyWrapper wrapper, int staffCount, List<FeeTransportWrapper> feeTransports) {
        this.company = wrapper;
        this.staffCount = staffCount;
        this.feeTransports = feeTransports;
    }

    public CompanyWrapper getCompany() {
        return company;
    }

    public void setCompany(CompanyWrapper company) {
        this.company = company;
    }

    public int getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(int staffCount) {
        this.staffCount = staffCount;
    }

    public List<FeeTransportWrapper> getFeeTransports() {
        return feeTransports;
    }

    public void setFeeTransports(List<FeeTransportWrapper> feeTransports) {
        this.feeTransports = feeTransports;
    }
}
