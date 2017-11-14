package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Store;
import vn.needy.ecommerce.repository.StoreResponsitory;

@Repository("storeResponsitory")
public class StoreResponsitoryImpl implements StoreResponsitory {

	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(Store.TABLE)
        		.usingGeneratedKeyColumns("store_id");
    }
	
	@Override
	public long registerStore(Store store) {
		Map<String, Object> params = new HashMap<>(9);
		params.put("company_id", store.getCompanyId());
		params.put("state", store.getState());
		params.put("status", store.getStatus());
		params.put("name", store.getName());
		params.put("address", store.getAddress());
		params.put("opening_time", store.getOpeningTime());
		params.put("closing_time", store.getClosingTime());
		params.put("lat", store.getLat());
		params.put("lng", store.getLng());
		params.put("last_updated_by", store.getLastUpdatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

	@Override
	public int getLastStoreCode(long companyId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
