package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.Pay;

public interface PayRepository {

	long createPayLog(Pay payLog);
}
