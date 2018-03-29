package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.FeeTransport;
import vn.needy.ecommerce.model.wrapper.FeeTransportWrapper;

import java.util.List;

public interface FeeTransportRepository {

    List<FeeTransport> getListByProductId(long productId);
    void updateFeeTransport(long productId, long userId, List<FeeTransportWrapper> feeTransports);
    void removeFeeTransport(long productId, List<Long> ids);
}
