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
import vn.needy.ecommerce.model.json.request.ActiveAccountRequest;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.repository.UsersRepository;

@Repository("userRepository")
public class UsersRepositoryImpl implements UsersRepository {

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
		SqlRowSet rs = jdbc.queryForRowSet("SELECT fullName "
				+ "FROM Users "
				+ "WHERE username = ? AND state <> ?", 
				new Object[] {username, UserState.DELETED.getState()});
		if (rs.first()) {
			User user = new User();
			user.setFullName(rs.getString("fullName"));
			return user;
		}
		return null;
	}

	@Override
	public long registerUser(RegisterUserRequest registerInfo) {
		Map<String, Object> params = new HashMap<>(4);
		params.put("username", registerInfo.getUsername());
		params.put("password", registerInfo.getPassword());
		params.put("state", UserState.INACTIVE.getState());
		params.put("firebaseUid", registerInfo.getFirebaseUid());
		return insert.executeAndReturnKey(params).longValue();
	}

	@Override
	public User findUserForResponseById(long id) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT state, fullName, gender, address, "
				+ "avatar, coverPicture, email, birthday, lat, lng, createdTime, lastUpdatedTime, lastResetPassword "
				+ "FROM Users "
				+ "WHERE id = ?", new Object[]{id});
		if (rs.first()) {
			User user = new User();
			user.setState(rs.getInt("state"));
			user.setFullName(rs.getString("fullName"));
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
				+ "WHERE username = ? AND state <> ?", new Object[] {username, UserState.DELETED.getState()});
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

	@Override
	public int activeAccount(long userId, ActiveAccountRequest request) {
		Object params[] = new Object[] {
			UserState.ACTIVE.getState(),
			request.getFullName(),
			request.getAddress(),
			request.getLat(),
			request.getLng(),
			userId
		};
		String sqlUpdate = "UPDATE Users "
				+ "SET state =?, fullName = ?, address = ?, lat = ?, lng = ? "
				+ "WHERE id = ?";
		return jdbc.update(sqlUpdate, params);
	}

}
