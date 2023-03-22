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
import be.model.Bill;
import be.singleton.BDDConnection;

public class BillSQL extends AbstractDao<Bill,Integer> {
	
	public BillSQL(Connection conn) {
		super(conn);
		this.conn = BDDConnection.getInstance();
		// TODO Auto-generated constructor stub
	}
	
	
	private static final String SQLGETALL = "SELECT * FROM BILL";
	private static final String SQLGETFROMID = "SELECT * FROM BILL WHERE billnumber=?";
	private static final String SQLGETLISTFROMREGEX = "SELECT * FROM BILL WHERE vattotal REGEXP ? or vatexclude REGEXP ? or totalPrice REGEXP ?";
	private static final String SQLINSERT = "INSERT INTO BILL ('billnumber',`billdate`, `vattotal`, `vatexclude`, `totalPrice`) VALUES(?,?,?,?,?)";
	private static final String SQLDELETE = "DELETE FROM BILL WHERE billnumber = ?";
	private static final String SQLUPDATE = "UPDATE BILL SET vattotal = ?, vatexclude = ?, totalPrice = ? WHERE billnumber = ?";
	private static final String SQLCOUNT = "SELECT COUNT(*) FROM BILL";
	private static final String SQLGETLISTFROMBIRTHDATE = "SELECT * FROM BILL WHERE billdate = ?";
	
	

	@Override
	public List<Bill> getListe() {
		List<Bill> listeBills = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETALL)) {
			var rs = ps.executeQuery();
			LocalDate billdate;
			while (rs.next()) {
				billdate = rs.getDate("billdate").toLocalDate();
				listeBills.add(new Bill(rs.getInt("billnumber"),billdate,rs.getFloat("vattotal"),rs.getFloat("vatexclude"),rs.getFloat("totalPrice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeBills;
	}

	@Override
	public Optional<Bill> getFromID(Integer billnumber) {
		Bill bill = null;
		LocalDate billdate = null;
		try (PreparedStatement ps = conn.prepareStatement(SQLGETFROMID)) {
			ps.setInt(1, billnumber);
			var rs = ps.executeQuery();
			if (rs.next())
				billdate = rs.getDate("billdate").toLocalDate();
				bill = new Bill(rs.getInt("billnumber"), billdate,rs.getFloat("vattotal"),rs.getFloat("vatexclude"),rs.getFloat("totalPrice"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(bill);
	}

	@Override
	public List<Bill> getListe(String regExpr) {
		List<Bill> listeBills = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETLISTFROMREGEX)) {
			for (int i = 1; i < 4; i++) {
				ps.setString(i, regExpr);
			}
			var rs = ps.executeQuery();
			LocalDate billdate;
			while (rs.next()) {
				billdate = rs.getDate("billdate").toLocalDate();
				listeBills.add(new Bill(rs.getInt("billnumber"),billdate,rs.getFloat("vattotal"),rs.getFloat("vatexclude"),rs.getFloat("totalPrice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeBills;
	}

	@Override
	public boolean insert(Bill bill) throws Exception {
		boolean BillInserted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLINSERT)) {
			ps.setInt(1, bill.getBillnumber());
			ps.setDate(2, java.sql.Date.valueOf(bill.getBilldate()));
			ps.setFloat(3, bill.getVattotal());
			ps.setFloat(4, bill.getVatexclude());
			ps.setFloat(5, bill.getTotalPrice());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				BillInserted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return BillInserted;
	}

	
	@Override
	public boolean delete(Bill bill) throws Exception {
		boolean BillDeleted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLDELETE)) {
			ps.setInt(1, bill.getBillnumber());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				BillDeleted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return BillDeleted;
	}

	@Override
	public boolean update(Bill bill) throws Exception {
		boolean BillUpdated = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLUPDATE)) {
			ps.setInt(1, bill.getBillnumber());
			ps.setFloat(2, bill.getVattotal());
			ps.setFloat(3, bill.getVatexclude());
			ps.setFloat(4, bill.getTotalPrice());
			ps.setDate(5, java.sql.Date.valueOf(bill.getBilldate()));
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				BillUpdated = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return BillUpdated;
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

	public List<Bill> getBillFromBilldate(LocalDate billdate) {
		List<Bill> listeBills = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETLISTFROMBIRTHDATE)) {
			ps.setDate(1, java.sql.Date.valueOf(billdate));
			var rs = ps.executeQuery();
			while (rs.next()) {
				billdate = rs.getDate("billdate").toLocalDate();
				listeBills.add(new Bill(rs.getInt("billnumber"),billdate,rs.getFloat("vattotal"),rs.getFloat("vatexclude"),rs.getFloat("totalPrice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeBills;
	}
}
