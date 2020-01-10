package com.sunbeaminfo.internship.library.daos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Dao implements AutoCloseable {
	public enum TxState {
		None, InProgress, Committed, Rollbacked
	}
	
	private static Properties props = loadJdbcProperties();
	protected Connection con;
	protected TxState txState;
	
	public Dao() {
		this.txState = TxState.None;
	}
	
	public Connection open() throws Exception {
		con = DriverManager.getConnection(
				props.getProperty("jdbc.url"), 
				props.getProperty("jdbc.user"), 
				props.getProperty("jdbc.password"));
		return con;
	}
	
	public Connection openTx() throws Exception {
		open();
		con.setAutoCommit(false);
		this.txState = TxState.InProgress;
		return con;
	}
	
	public void commit() throws Exception {
		if(this.txState == TxState.InProgress) {
			con.commit();
			this.txState = TxState.Committed;
		}
	}
	
	public void rollback() throws SQLException {
		if(this.txState == TxState.InProgress) {
			con.rollback();
			this.txState = TxState.Rollbacked;
		}
	}

	@Override
	public void close() {
		try {
			if(con != null) {
				if(this.txState == TxState.InProgress)
					rollback();
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Properties loadJdbcProperties() {
		Properties props = new Properties();
		try(InputStream in = Dao.class.getResourceAsStream("/jdbc.properties")) {
			props.load(in);
			Class.forName(props.getProperty("jdbc.driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}
}
