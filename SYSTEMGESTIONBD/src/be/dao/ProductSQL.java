package be.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.abstractDao.AbstractDao;
import be.model.Product;
import be.singleton.BDDConnection;

public class ProductSQL extends AbstractDao<Product,Integer> {
	
	private static final String SQLGETALL = "SELECT * FROM PRODUCT";
	private static final String SQLGETFROMID = "SELECT * FROM PRODUCT WHERE PRODUCT_REF=?";
	private static final String SQLGETLISTFROMREGEX = "SELECT * FROM PRODUCT WHERE productName REGEXP ? or unitprice REGEXP ?";
	private static final String SQLINSERT = "INSERT INTO PRODUCT (`productName`, `unitprice`) VALUES(?,?)";
	private static final String SQLDELETE = "DELETE FROM PRODUCT WHERE productName = ?";
	private static final String SQLUPDATE = "UPDATE PRODUCT SET productName = ?, unitprice = ? WHERE productName = ?";
	private static final String SQLCOUNT = "SELECT COUNT(*) FROM PRODUCT";
	
	
	
	public ProductSQL(Connection conn) {
		super(conn);
		this.conn = BDDConnection.getInstance();;
	}

	
	@Override
	public List<Product> getListe() {
		List<Product> listeProducts = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETALL)) {
			var rs = ps.executeQuery();
			while (rs.next()) {
				listeProducts.add(new Product(rs.getString("productName"),rs.getFloat("unitprice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeProducts;
	}
	
	
	@Override
	public Optional<Product> getFromID(Integer id) {
		Product product = null;
		try (PreparedStatement ps = conn.prepareStatement(SQLGETFROMID)) {
			ps.setInt(1, id);
			var rs = ps.executeQuery();
			if (rs.next())
				product = new Product(rs.getString("productName"),rs.getFloat("unitprice"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(product);
	}

	@Override
	public List<Product> getListe(String regExpr) {
		List<Product> listeProducts = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(SQLGETLISTFROMREGEX)) {
			for (int i = 1; i < 3; i++) {
				ps.setString(i, regExpr);
			}
			var rs = ps.executeQuery();
			while (rs.next()) {
				listeProducts.add(new Product(rs.getString("productName"),rs.getFloat("unitprice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeProducts;
	}

	@Override
	public boolean insert(Product product) throws Exception {
		boolean ProductInserted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLINSERT)) {
			ps.setString(1,product.getProductName());
			ps.setFloat(2, product.getUnitprice());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				ProductInserted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ProductInserted;
	}

	
	@Override
	public boolean delete(Product product) throws Exception {
		boolean ProductDeleted = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLDELETE)) {
			ps.setString(1, product.getProductName());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				ProductDeleted = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ProductDeleted;
	}

	@Override
	public boolean update(Product product) throws Exception {
		boolean ProductUpdated = false;
		try (PreparedStatement ps = conn.prepareStatement(SQLUPDATE)) {
			ps.setString(1, product.getProductName());
			ps.setFloat(2, product.getUnitprice());
			int insertionChecking = ps.executeUpdate();
			if (insertionChecking > 0) {
				ProductUpdated = true;
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ProductUpdated;
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
