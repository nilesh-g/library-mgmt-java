package com.sunbeaminfo.internship.library.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.internship.library.entities.Payment;

public class PaymentDao {
	private Connection con;
	
	public PaymentDao(Connection con) {
		this.con = con;
	}
	
	public Payment findPaymentById(int id) throws Exception {
		String sql = "SELECT id, memberid, amount, type, txtime, duedate FROM payments WHERE id=%s";
		Payment record = new Payment();
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					record.setId(id);
					record.setMemberId(rs.getInt("memberid"));
					record.setType(rs.getString("type"));
					record.setAmount(rs.getDouble("amount"));
					record.setTxTime(rs.getTimestamp("txtime"));
					record.setDueDate(rs.getTimestamp("duedate"));
					return record;
				}
			}
			return null;
		}
	}
	
	public List<Payment> findPaymentByMember(int memberId, String type, boolean onlyLastPayment) throws Exception {
		String sql = "";
		if(type.equals(Payment.FINE))
			sql = "SELECT id, memberid, amount, type, txtime, duedate FROM payments WHERE memberid=? AND type='fee' ORDER BY txtime DESC";
		else if(type.equals(Payment.FEE))
			sql = "SELECT id, memberid, amount, type, txtime, duedate FROM payments WHERE memberid=? AND type='fine' ORDER BY txtime DESC";
		else
			sql = "SELECT id, memberid, amount, type, txtime, duedate FROM payments WHERE memberid=? ORDER BY txtime DESC";
		if(onlyLastPayment)
			sql += " LIMIT 1";
		List<Payment> list = new ArrayList<Payment>();
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, memberId);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					Payment record = new Payment();
					record.setId(rs.getInt("id"));
					record.setMemberId(rs.getInt("memberid"));
					record.setType(rs.getString("type"));
					record.setAmount(rs.getDouble("amount"));
					record.setTxTime(rs.getTimestamp("txtime"));
					record.setDueDate(rs.getTimestamp("duedate"));
					list.add(record);
				}
			}
			return list;
		}
	}

	public int insertPayment(Payment record) throws Exception {
		String sql = "INSERT INTO payments(memberid, amount, type, txtime, duedate) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, record.getMemberId());
			stmt.setDouble(2, record.getAmount());
			stmt.setString(3, record.getType());
			stmt.setTimestamp(4, record.getTxTime());
			stmt.setTimestamp(5, record.getDueDate());
			return stmt.executeUpdate();
		}
	}
	
	public int updatePayment(Payment record) throws Exception {
		String sql = "UPDATE payments SET memberid=?, amount=?, type=?, txtime=?, duedate=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, record.getMemberId());
			stmt.setDouble(2, record.getAmount());
			stmt.setString(3, record.getType());
			stmt.setTimestamp(4, record.getTxTime());
			stmt.setTimestamp(5, record.getDueDate());
			stmt.setInt(6, record.getId());
			return stmt.executeUpdate();
		}
	}
}
