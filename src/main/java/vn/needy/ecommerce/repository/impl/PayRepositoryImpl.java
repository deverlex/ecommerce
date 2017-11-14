package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Pay;
import vn.needy.ecommerce.repository.PayRepository;

@Repository("payRepository")
public class PayRepositoryImpl implements PayRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(Pay.TABLE)
        		.usingGeneratedKeyColumns("pay_id");
    }
	
	@Override
	public long createPayLog(Pay payLog) {
		Map<String, Object> params = new HashMap<>(6);
		params.put("budget_id", payLog.getBudgetId());
		params.put("behavior", payLog.getBehavior());
		params.put("pay_number", payLog.getPayNumber());
		params.put("budget_charge", payLog.getBudgetCharge());
		params.put("description", payLog.getDescription());
		params.put("created_by", payLog.getCreatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

}
