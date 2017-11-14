package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Budget;
import vn.needy.ecommerce.repository.BudgetRepository;

@Repository("budgetRepository")
public class BudgetRepositoryImpl implements BudgetRepository {

	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(Budget.TABLE)
        		.usingGeneratedKeyColumns("budget_id");
    }
	
	@Override
	public long createBudget(Budget budget) {
		Map<String, Object> params = new HashMap<>(2);
		params.put("company_id", budget.getCompanyId());
		params.put("budget", budget.getBudget());
		return insert.executeAndReturnKey(params).longValue();
	}

}
