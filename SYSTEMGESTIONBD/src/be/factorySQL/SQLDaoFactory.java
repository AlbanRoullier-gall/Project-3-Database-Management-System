package be.factorySQL;

import java.sql.Connection;

import be.abstractDaoFactory.DaoFactory;
import be.dao.BillSQL;
import be.dao.CustomerSQL;
import be.dao.ProductSQL;
import be.dao.UserSQL;
import be.singleton.BDDConnection;

public class SQLDaoFactory extends DaoFactory {

	protected static final Connection conn = BDDConnection.getInstance();
	
	@Override
	public Connection getConnection() {
		return SQLDaoFactory.conn;
	}

	@Override
	public UserSQL getUserDAO() {
		return new UserSQL(conn);
	}

	public BillSQL getBillDAO() {
		return new BillSQL(conn);
	}

	
	public CustomerSQL getCustomerDAO() {
		return new CustomerSQL(conn);
	}

	
	public ProductSQL getProductDAO() {
		return new ProductSQL(conn);
	}

	

}
