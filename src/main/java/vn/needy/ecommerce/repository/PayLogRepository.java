package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.PayLog;

public interface PayLogRepository {

	long createPayLog(PayLog payLog);
}
