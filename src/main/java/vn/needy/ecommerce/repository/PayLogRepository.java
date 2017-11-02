package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.Pays;

public interface PayLogRepository {

	long createPayLog(Pays payLog);
}
