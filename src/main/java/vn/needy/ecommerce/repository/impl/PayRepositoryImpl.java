package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.mysql.Pay;
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
        		.usingGeneratedKeyColumns("id");
    }
	
	@Override
	public long createPay(Pay pay) {
		Map<String, Object> params = new HashMap<>(6);
		params.put("budget_id", pay.getBudgetId());
		params.put("behavior", pay.getBehavior());
		params.put("budget_charge", pay.getBudgetCharge());
		params.put("description", pay.getDescription());
		params.put("created_by", pay.getCreatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

}
