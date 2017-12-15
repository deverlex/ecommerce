package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.FeeTransport;
import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;

import java.util.List;

public interface FeeTransportRepository {

    List<FeeTransport> getListByCompanyId(long companyId);
    void updateFeeTransport(long companyId, long userId, List<FeeTransportWrapper> feeTransports);
    void removeFeeTransport(long companyId, List<Long> ids);
}
