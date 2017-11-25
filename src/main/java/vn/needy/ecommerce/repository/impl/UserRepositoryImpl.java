package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.api.v1.user.request.RegisterUserRequest;
import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	MongoTemplate mongo;
	
	private SimpleJdbcInsert insert;

	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(User.TABLE)
        		.usingGeneratedKeyColumns("user_id");
    }

	// This function using for authorization (login) 
	@Override
	public User findUserByUsernameForAuthenticate(String username) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT user_id, username, password, state, unlock_time, last_reset_password "
				+ "FROM user "
				+ "WHERE username = ? AND state <> ?", new Object[] {username, UserState.DELETED.getState()});
		if (rs.first()) {
			User user = new User();
			user.setId(rs.getLong("user_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setState(rs.getInt("state"));
			user.setUnlockTime(rs.getTimestamp("unlock_time"));
			user.setLastResetPassword(rs.getTimestamp("last_reset_password"));
			return user;
		}
		return null;
	}

	@Override
	public boolean updateUserState(long id, int state) {
		return jdbc.update("UPDATE user SET state = ? WHERE id = ?", 
				new Object[] {state, id}) == 1;
	}

	@Override
	public String findUserExistByUsername(String username) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT full_name "
				+ "FROM user "
				+ "WHERE username = ? AND state <> ?", 
				new Object[] {username, UserState.DELETED.getState()});
		if (rs.first()) {
			return rs.getString("full_name");
		}
		return null;
	}

	@Override
	public long registerUser(RegisterUserRequest registerInfo) {
		Map<String, Object> params = new HashMap<>(4);
		params.put("username", registerInfo.getUsername());
		params.put("password", registerInfo.getPassword());
		params.put("state", UserState.INACTIVE.getState());
		params.put("firebase_uid", registerInfo.getFirebaseUid());
		params.put("full_name", "No Name");
		return insert.executeAndReturnKey(params).longValue();
	}

	@Override
	public User findUserById(long id) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT state, full_name, address, "
				+ "lat, lng, created_time, last_updated_time, last_reset_password "
				+ "FROM user "
				+ "WHERE user_id = ?", new Object[]{id});
		if (rs.first()) {
			User user = new User();
			user.setState(rs.getInt("state"));
			user.setFullName(rs.getString("full_name"));
			user.setAddress(rs.getString("address"));
			user.setLat(rs.getFloat("lat"));
			user.setLng(rs.getFloat("lng"));
			user.setCreatedTime(rs.getTimestamp("created_time"));
			user.setLastUpdatedTime(rs.getTimestamp("last_updated_time"));
			user.setLastResetPassword(rs.getTimestamp("last_reset_password"));
			return user;
		}
		return null;
	}

	@Override
	public User findUserByUsernameForResetPassword(String username) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT user_id, firebase_uid "
				+ "FROM user "
				+ "WHERE username = ? AND state <> ?", new Object[] {username, UserState.DELETED.getState()});
		if (rs.first()) {
			User user = new User();
			user.setId(rs.getLong("user_id"));
			user.setFirebaseUid(rs.getString("firebase_uid"));
			return user;
		}
		return null;
	}

	@Override
	public boolean updatePasswordByUserId(long id, String password) {
		return jdbc.update("UPDATE user SET password = ? WHERE user_id = ?", 
				new Object[] {password, id}) == 1;
	}

}
