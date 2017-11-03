package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.Pay;

public interface PaysRepository {

	long createPayLog(Pay payLog);
}
