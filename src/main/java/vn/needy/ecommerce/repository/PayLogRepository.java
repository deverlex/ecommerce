package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.Pay;

public interface PayLogRepository {

	long createPayLog(Pay payLog);
}
