package vn.needy.ecommerce.api.v1.company.response;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;

import java.util.List;

public class CompanyInfoResp extends BaseResponse {

    private CompanyWrapper company;
    private int totalStaff;
    private List<FeeTransportWrapper> feeTransport;

    public CompanyInfoResp(CompanyWrapper wrapper, int staffCount) {
        this.company = wrapper;
        this.totalStaff = staffCount;
    }

    public CompanyInfoResp(CompanyWrapper wrapper, int staffCount, List<FeeTransportWrapper> feeTransports) {
        this.company = wrapper;
        this.totalStaff = staffCount;
        this.feeTransport = feeTransports;
    }

    public CompanyWrapper getCompany() {
        return company;
    }

    public void setCompany(CompanyWrapper company) {
        this.company = company;
    }

    public int getTotalStaff() {
        return totalStaff;
    }

    public void setTotalStaff(int totalStaff) {
        this.totalStaff = totalStaff;
    }

    public List<FeeTransportWrapper> getFeeTransport() {
        return feeTransport;
    }

    public void setFeeTransport(List<FeeTransportWrapper> feeTransport) {
        this.feeTransport = feeTransport;
    }
}
