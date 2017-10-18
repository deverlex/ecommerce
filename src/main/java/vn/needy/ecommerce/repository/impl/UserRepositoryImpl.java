package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(User.TABLE)
        		.usingGeneratedKeyColumns("id");
    }

	@Override
	public User findUserByUsernameForAuthenticate(String username) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT id, username, password, state, unlockTime, lastResetPassword "
				+ "FROM Users "
				+ "WHERE username = ? AND state <> ?", new Object[] {username, UserState.DELETED.getState()});
		if (rs.first()) {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setState(rs.getInt("state"));
			user.setUnlockTime(rs.getTimestamp("unlockTime"));
			user.setLastResetPassword(rs.getTimestamp("lastResetPassword"));
			return user;
		}
		return null;
	}

	@Override
	public boolean updateUserState(long id, int state) {
		return jdbc.update("UPDATE Users SET state = ? WHERE id = ?", 
				new Object[] {state, id}) == 1;
	}

	@Override
	public User findUserExistByUsername(String username) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT firstName, lastName "
				+ "FROM Users "
				+ "WHERE username = ? AND state <> ?", 
				new Object[] {username, UserState.DELETED.getState()});
		if (rs.first()) {
			User user = new User();
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			return user;
		}
		return null;
	}

	@Override
	public long registerUser(RegisterUserRequest registerInfo) {
		Map<String, Object> params = new HashMap<>(11);
		params.put("username", registerInfo.getUsername());
		params.put("password", registerInfo.getPassword());
		params.put("state", UserState.ACTIVE.getState());
		params.put("firebaseUid", registerInfo.getFirebaseUid());
		params.put("firstName", registerInfo.getFirstName());
		params.put("lastName", registerInfo.getLastName());
		params.put("gender", registerInfo.getGender());
		params.put("address", registerInfo.getAddress());
		params.put("lat", registerInfo.getLat());
		params.put("lng", registerInfo.getLng());
		return insert.executeAndReturnKey(params).longValue();
	}

	@Override
	public User findUserForResponseById(long id) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT username, firstName, lastName, gender, address, "
				+ "avatar, coverPicture, email, birthday, lat, lng, createdTime, lastUpdatedTime, lastResetPassword "
				+ "WHERE id = ?", new Object[]{id});
		if (rs.first()) {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setGender(rs.getString("gender"));
			user.setAddress(rs.getString("address"));
			user.setAvatar(rs.getString("avatar"));
			user.setCoverPicture(rs.getString("coverPicture"));
			user.setEmail(rs.getString("email"));
			user.setBirthday(rs.getDate("birthday"));
			user.setLat(rs.getFloat("lat"));
			user.setLng(rs.getFloat("lng"));
			user.setCreatedTime(rs.getTimestamp("createdTime"));
			user.setLastUpdatedTime(rs.getTimestamp("lastUpdatedTime"));
			user.setLastResetPassword(rs.getTimestamp("lastResetPassword"));
			return user;
		}
		return null;
	}

	@Override
	public User findUserByUsernameForResetPassword(String username) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT id, firebaseUid "
				+ "FROM Users "
				+ "WHERE username = ? AND state <> ?", new Object[] {username, UserState.DELETED});
		if (rs.first()) {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setFirebaseUid(rs.getString("firebaseUid"));
			return user;
		}
		return null;
	}

	@Override
	public boolean updatePasswordByUserId(long id, String password) {
		return jdbc.update("UPDATE Users SET password = ? WHERE id = ?", 
				new Object[] {password, id}) == 1;
	}

}
