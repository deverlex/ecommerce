package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.api.v1.store.request.UpdateStoreInfoReq;
import vn.needy.ecommerce.domain.mysql.Store;
import vn.needy.ecommerce.repository.StoreRepository;

@Repository("storeResponsitory")
public class StoreRepositoryImpl implements StoreRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	MongoTemplate mongo;
	
	@Autowired
	MongoOperations operations;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(Store.TABLE)
        		.usingGeneratedKeyColumns("id");
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
	public Store getOurByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("select s.* from user u " + 
				"inner join company_staff cs on cs.user_id = u.id " +
				"inner join store s on s.id = cs.store_id " +
				"where u.id = ?", userId);
		if (rs.first()) {
			Store store = new Store();
			store.setId(rs.getLong("id"));
			store.setCompanyId(rs.getLong("company_id"));
			store.setState(rs.getInt("state"));
			store.setStatus(rs.getInt("status"));
			store.setUnlockTime(rs.getDate("unlock_time"));
			store.setName(rs.getString("name"));
			store.setAddress("address");
			store.setLat(rs.getFloat("lat"));
			store.setLng(rs.getFloat("lng"));
			store.setOpeningTime(rs.getTimestamp("opening_time"));
			store.setClosingTime(rs.getTimestamp("closing_time"));
			store.setCreatedTime(rs.getDate("created_time"));
			store.setLastUpdatedTime(rs.getDate("last_updated_time"));
			store.setLastUpdatedBy(rs.getLong("last_updated_by"));
			return store;
		}
		return null;
	}

	@Override
	public Map getStoreInformation(long storeId) {
		SqlRowSet rs = jdbc.queryForRowSet("select s.* from store s where id = ?",
				storeId);
		if (rs.first()) {
			Map map = new HashMap();
			Store store = new Store();
			store.setId(rs.getLong("id"));
			store.setCompanyId(rs.getLong("company_id"));
			store.setState(rs.getInt("state"));
			store.setStatus(rs.getInt("status"));
			store.setUnlockTime(rs.getDate("unlock_time"));
			store.setName(rs.getString("name"));
			store.setAddress(rs.getString("address"));
			store.setEmail(rs.getString("email"));
			store.setDescription(rs.getString("description"));
			store.setLat(rs.getFloat("lat"));
			store.setLng(rs.getFloat("lng"));
			store.setOpeningTime(rs.getTimestamp("opening_time"));
			store.setClosingTime(rs.getTimestamp("closing_time"));
			store.setCreatedTime(rs.getDate("created_time"));
			store.setLastUpdatedTime(rs.getDate("last_updated_time"));
			store.setLastUpdatedBy(rs.getLong("last_updated_by"));
			map.put("store", store);
			return map;
		}
		return null;
	}

	@Override
	public boolean updateStoreInformation(long storeId, UpdateStoreInfoReq req) {
		return false;
	}

}
