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

import vn.needy.ecommerce.domain.entity.Store;
import vn.needy.ecommerce.domain.mongo.StoreDetail;
import vn.needy.ecommerce.repository.StoreResponsitory;

@Repository("storeResponsitory")
public class StoreResponsitoryImpl implements StoreResponsitory {

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
	public Store getStore(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("select s.* from user u " + 
				"inner join company_staff cs on cs.user_id = u.user_id " + 
				"inner join store s on s.store_id = cs.store_id " + 
				"where u.user_id = ?", new Object[] {userId});
		if (rs.first()) {
			Store store = new Store();
			store.setId(rs.getLong("store_id"));
			store.setCompanyId(rs.getLong("company_id"));
			store.setState(rs.getInt("state"));
			store.setStatus(rs.getInt("status"));
			store.setUnlockTime(rs.getDate("unlock_time"));
			store.setName(rs.getString("name"));
			store.setAddress("address");
			store.setLat(rs.getFloat("lat"));
			store.setLng(rs.getFloat("lng"));
			store.setOpeningTime(rs.getDate("opening_time"));
			store.setClosingTime(rs.getDate("closing_time"));
			store.setCreatedTime(rs.getDate("created_time"));
			store.setLastUpdatedTime(rs.getDate("last_updated_time"));
			store.setLastUpdatedBy(rs.getLong("last_updated_by"));
			return store;
		}
		return null;
	}

	@Override
	public StoreDetail getStoreDetail(long storeId) {
		
		StoreDetail storeDetail =  mongo.findById(storeId, StoreDetail.class);
		return storeDetail;
	}

//	@Override
//	public List<StorePicture> getStorePictures(long storeId) {
//		// Dang can xem lai
//		Query query = new Query();
//		Long id = new Long(storeId);
//		query.addCriteria(new Criteria().exists(
//				Criteria.where("store_id").equals(id)
//			));
//		List<StorePicture> storePictures = mongo.find(query, StorePicture.class);
//		return storePictures;
//	}

}
