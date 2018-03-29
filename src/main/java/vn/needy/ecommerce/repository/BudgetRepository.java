package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.Budget;

public interface BudgetRepository {

	long createBudget(Budget budget);
}
