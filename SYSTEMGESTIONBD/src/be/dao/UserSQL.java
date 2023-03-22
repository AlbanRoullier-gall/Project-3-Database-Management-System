package be.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.abstractDao.AbstractDao;
import be.model.User;
import be.singleton.BDDConnection;

public class UserSQL extends AbstractDao<User,Integer> implements InterfaceUserDAO{

	private static final String SQLGETALL = "SELECT * FROM USER";
	private static final String SQLGETFROMID = "SELECT * FROM USER WHERE USER_ID=?";
	private static final String SQLGETLISTFROMREGEX = "SELECT * FROM USER WHERE username REGEXP ? or password REGEXP ? or name REGEXP ? or firstname REGEXP ? or gender REGEXP ?";
	private static final String SQLINSERT = "INSERT INTO USER (`username`, `password`, `name`, `firstname`, `gender`, `birthdate`) VALUES(?,?,?,?,?,?)";
	private static final String SQLDELETE = "DELETE FROM USER WHERE USERNAME = ?";
	private static final String SQLUPDATE = "UPDATE USER SET NAME = ?, FIRSTNAME = ?, GENDER = ? WHERE USERNAME = ?";
	private static final String SQLCOUNT = "SELECT COUNT(*) FROM USER";
	private static final String SQLGETLISTFROMBIRTHDATE = "SELECT * FROM USER WHERE BIRTHDATE = ?";
	
	
	public UserSQL(Connection conn) {
		super(conn);
		this.conn = BDDConnection.getInstance();
	}
	
	
	@Override
	public List<User> getListe() {
		List<User> listeUsers = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETALL)) {
			var rs = ps.executeQuery();
			LocalDate birthdate;
			while (rs.next()) {
				birthdate = rs.getDate("birthdate").toLocalDate();
				listeUsers.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getString("firstname"),rs.getString("gender"), birthdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeUsers;
	}

	@Override
	public Optional<User> getFromID(Integer id) {
		User user = null;
		LocalDate birthdate = null;
		try (PreparedStatement ps = conn.prepareStatement(SQLGETFROMID)) {
			ps.setInt(1, id);
			var rs = ps.executeQuery();
			if (rs.next())
				birthdate = rs.getDate("birthdate").toLocalDate();
				user = new User(rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getString("firstname"),rs.getString("gender"), birthdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> getListe(String regExpr) {
		List<User> listeUsers = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETLISTFROMREGEX)) {
			for (int i = 1; i < 6; i++) {
				ps.setString(i, regExpr);
			}
			var rs = ps.executeQuery();
			LocalDate birthdate;
			while (rs.next()) {
				birthdate = rs.getDate("birthdate").toLocalDate();
				listeUsers.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getString("firstname"),rs.getString("gender"), birthdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeUsers;
	}

	@Override
	public boolean insert(User user) throws Exception {
		boolean UserInserted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLINSERT)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getFirstname());
			ps.setString(5, user.getGender());
			ps.setDate(6, java.sql.Date.valueOf(user.getBirthdate()));
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				UserInserted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserInserted;
	}

	
	@Override
	public boolean delete(User user) throws Exception {
		boolean UserDeleted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLDELETE)) {
			ps.setString(1, user.getUsername());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				UserDeleted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserDeleted;
	}

	@Override
	public boolean update(User user) throws Exception {
		boolean UserUpdated = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLUPDATE)) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getFirstname());
			ps.setString(3, user.getGender());
			ps.setString(4, user.getUsername());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				UserUpdated = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return UserUpdated;
	}

	@Override
	public int count() {
		int res = 0;
		try (Statement st = conn.createStatement();) {
			var rs = st.executeQuery(SQLCOUNT);
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<User> getUserFromBirthdate(LocalDate birthdate) {
		List<User> listeUsers = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETLISTFROMBIRTHDATE)) {
			ps.setDate(1, java.sql.Date.valueOf(birthdate));
			var rs = ps.executeQuery();
			while (rs.next()) {
				birthdate = rs.getDate("birthdate").toLocalDate();
				listeUsers.add(new User(rs.getString("username"),rs.getString("password"),rs.getString("name"),rs.getString("firstname"),rs.getString("gender"), birthdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeUsers;
	}
		
}
