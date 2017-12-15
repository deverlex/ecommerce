package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.api.v1.company.request.FeeTransportReq;
import vn.needy.ecommerce.domain.mysql.FeeTransport;

import java.util.List;

public interface FeeTransportRepository {

    List<FeeTransport> getListByCompanyId(long companyId);
    boolean updateFeeTransport(long companyId, long userId, List<FeeTransportReq> feeTransports);
}
