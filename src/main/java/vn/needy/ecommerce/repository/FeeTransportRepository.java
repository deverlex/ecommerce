package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.FeeTransport;

import java.util.List;

public interface FeeTransportRepository {

    List<FeeTransport> getListByCompanyId(long companyId);
}
