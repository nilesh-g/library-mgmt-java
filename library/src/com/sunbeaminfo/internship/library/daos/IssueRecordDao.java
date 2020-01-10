package com.sunbeaminfo.internship.library.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.internship.library.entities.IssueRecord;

public class IssueRecordDao {
	private Connection con;
	
	public IssueRecordDao(Connection con) {
		this.con = con;
	}
	
	public IssueRecord findIssueRecordById(int id) throws Exception {
		String sql = "SELECT id, copyid, memberid, issued, returndue, returned, fine FROM issuerecord WHERE id=?";
		IssueRecord record = new IssueRecord();
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					record.setId(id);
					record.setCopyId(rs.getInt("copyid"));
					record.setMemeberId(rs.getInt("memberid"));
					record.setIssued(rs.getTimestamp("issued"));
					record.setReturnDue(rs.getTimestamp("returndue"));
					record.setReturned(rs.getTimestamp("returned"));
					record.setFine(rs.getObject("fine")==null?null:rs.getDouble("fine"));
					return record;
				}
			}
			return null;
		}
	}
	
	public List<IssueRecord> findIssueRecordByMember(int memberId, String status) throws Exception {
		String sql = "";
		if(status.equals(IssueRecord.ISSUED))
			sql = "SELECT id, copyid, memberid, issued, returndue, returned, fine FROM issuerecord WHERE returned IS NULL";
		else if(status.equals(IssueRecord.RETURNED))
			sql = "SELECT id, copyid, memberid, issued, returndue, returned, fine FROM issuerecord WHERE returned IS NOT NULL";
		else
			sql = "SELECT id, copyid, memberid, issued, returndue, returned, fine FROM issuerecord WHERE 0";
		if(memberId > 0)
			sql += " AND memberid=?";
		List<IssueRecord> list = new ArrayList<IssueRecord>();
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			if(memberId > 0)
				stmt.setInt(1, memberId);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					IssueRecord record = new IssueRecord();
					record.setId(rs.getInt("id"));
					record.setCopyId(rs.getInt("copyid"));
					record.setMemeberId(rs.getInt("memberid"));
					record.setIssued(rs.getTimestamp("issued"));
					record.setReturnDue(rs.getTimestamp("returndue"));
					record.setReturned(rs.getTimestamp("returned"));
					record.setFine(rs.getObject("fine")==null?null:rs.getDouble("fine"));
					list.add(record);
				}
			}
			return list;
		}
	}

	public int insertIssueRecord(IssueRecord record) throws Exception {
		int newId = -1;
		String sql = "INSERT INTO issuerecord(copyid, memberid, issued, returndue, returned, fine) VALUES (?, ?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, record.getCopyId());
			stmt.setInt(2, record.getMemeberId());
			stmt.setTimestamp(3, record.getIssued());
			stmt.setTimestamp(4, record.getReturnDue());
			stmt.setTimestamp(5, record.getReturned());
			if(record.getFine() == null)
				stmt.setObject(6, null);
			else	
				stmt.setDouble(6, record.getFine());
			if(stmt.executeUpdate() > 0) {
				try(ResultSet rs = stmt.getGeneratedKeys()) {
					if(rs.next()) {
						newId = rs.getInt(1);
						record.setId(newId);
					}
				}
			}
		}
		return newId;
	}
	
	public int updateIssueRecord(IssueRecord record) throws Exception {
		String sql = "UPDATE issuerecord SET copyid=?, memberid=?, issued=?, returndue=?, returned=?, fine=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, record.getCopyId());
			stmt.setInt(2, record.getMemeberId());
			stmt.setTimestamp(3, record.getIssued());
			stmt.setTimestamp(4, record.getReturnDue());
			stmt.setTimestamp(5, record.getReturned());
			if(record.getFine() == null)
				stmt.setObject(6, null);
			else	
				stmt.setDouble(6, record.getFine());
			stmt.setInt(7, record.getId());
			return stmt.executeUpdate();
		}
	}
	
	public int deleteIssueRecord(int id) throws Exception {
		String sql = "DELETE FROM issuerecord WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		}
	}
}
