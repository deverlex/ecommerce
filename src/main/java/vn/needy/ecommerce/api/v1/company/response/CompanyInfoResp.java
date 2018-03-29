package vn.needy.ecommerce.api.v1.company.response;

import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;

import java.io.Serializable;
import java.util.List;

public class CompanyInfoResp implements Serializable {

    private CompanyWrapper company;
    private int totalStaff;

    public CompanyInfoResp(CompanyWrapper wrapper, int staffCount) {
        this.company = wrapper;
        this.totalStaff = staffCount;
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
}
