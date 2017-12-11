package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.Pay;

public interface PayRepository {

	long createPay(Pay pay);
}
