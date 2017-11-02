package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Pay;
import vn.needy.ecommerce.repository.PayLogRepository;

@Repository("payLogRepository")
public class PayLogRepositoryImpl implements PayLogRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(Pay.TABLE)
        		.usingGeneratedKeyColumns("id");
    }
	
	@Override
	public long createPayLog(Pay payLog) {
		Map<String, Object> params = new HashMap<>(5);
		params.put("budgetId", payLog.getBudgetId());
		params.put("behavior", payLog.getBehavior());
		params.put("payNumber", payLog.getPayNumber());
		params.put("budgetCharge", payLog.getBudgetCharge());
		params.put("description", payLog.getDescription());
		params.put("createdBy", payLog.getCreatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

}
