package be.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.abstractDao.AbstractDao;
import be.model.Customer;
import be.singleton.BDDConnection;

public class CustomerSQL extends AbstractDao<Customer,Integer>{

	private static final String SQLGETALL = "SELECT * FROM CUSTOMER";
	private static final String SQLGETFROMID = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
	private static final String SQLGETLISTFROMREGEX = "SELECT * FROM CUSTOMER WHERE customernumber REGEXP ? or name REGEXP ? or firstname REGEXP ? or tvanumber REGEXP ?";
	private static final String SQLINSERT = "INSERT INTO CUSTOMER (`customernumber`, `name`, `firstname`, `tvanumber`) VALUES(?,?,?,?)";
	private static final String SQLDELETE = "DELETE FROM CUSTOMER WHERE customernumber = ?";
	private static final String SQLUPDATE = "UPDATE CUSTOMER SET NAME = ?, FIRSTNAME = ?, tvanumber = ? WHERE customernumber = ?";
	private static final String SQLCOUNT = "SELECT COUNT(*) FROM CUSTOMER";
	
	
	public CustomerSQL(Connection conn) {
		super(conn);
		this.conn = BDDConnection.getInstance();
	}
	
	
	@Override
	public List<Customer> getListe() {
		List<Customer> listeCustomers = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETALL)) {
			var rs = ps.executeQuery();
			while (rs.next()) {
				listeCustomers.add(new Customer(rs.getInt("customernumber"),rs.getString("name"),rs.getString("firstname"),rs.getString("tvanumber")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCustomers;
	}

	@Override
	public Optional<Customer> getFromID(Integer id) {
		Customer customer = null;
		try (PreparedStatement ps = conn.prepareStatement(SQLGETFROMID)) {
			ps.setInt(1, id);
			var rs = ps.executeQuery();
			if (rs.next())
				customer = new Customer(rs.getInt("customernumber"),rs.getString("name"),rs.getString("firstname"),rs.getString("tvanumber"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(customer);
	}

	@Override
	public List<Customer> getListe(String regExpr) {
		List<Customer> listeCustomers = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETLISTFROMREGEX)) {
			for (int i = 1; i < 5; i++) {
				ps.setString(i, regExpr);
			}
			var rs = ps.executeQuery();
			while (rs.next()) {
				listeCustomers.add(new Customer(rs.getInt("customernumber"),rs.getString("name"),rs.getString("firstname"),rs.getString("tvanumber")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCustomers;
	}

	@Override
	public boolean insert(Customer customer) throws Exception {
		boolean CustomerInserted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLINSERT)) {
			ps.setInt(1, customer.getCustomernumber());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getFirstname());
			ps.setString(4, customer.getTvanumber());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				CustomerInserted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CustomerInserted;
	}

	
	@Override
	public boolean delete(Customer customer) throws Exception {
		boolean CustomerDeleted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLDELETE)) {
			ps.setInt(1, customer.getCustomernumber());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				CustomerDeleted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CustomerDeleted;
	}

	@Override
	public boolean update(Customer customer) throws Exception {
		boolean CustomerUpdated = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLUPDATE)) {
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getFirstname());
			ps.setString(3, customer.getTvanumber());
			ps.setInt(4,customer.getCustomernumber());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				CustomerUpdated = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return CustomerUpdated;
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

}
