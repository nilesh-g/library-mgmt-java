package com.sunbeaminfo.internship.library.entities;

import java.sql.Timestamp;

public class IssueRecord {
	public static final String ISSUED = "issued";
	public static final String RETURNED = "returned";

	private int id;
	private int copyId;
	private int memeberId;
	private Timestamp issued;
	private Timestamp returnDue;
	private Timestamp returned;
	private Double fine;
	public IssueRecord() {
		this(0, 0, 0, null, null, null, null);
	}
	public IssueRecord(int id, int copyId, int memeberId, Timestamp issued, Timestamp returnDue, Timestamp returned, Double fine) {
		this.id = id;
		this.copyId = copyId;
		this.memeberId = memeberId;
		this.issued = issued;
		this.returnDue = returnDue;
		this.returned = returned;
		this.fine = fine;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	public int getMemeberId() {
		return memeberId;
	}
	public void setMemeberId(int memeberId) {
		this.memeberId = memeberId;
	}
	public Timestamp getIssued() {
		return issued;
	}
	public void setIssued(Timestamp issued) {
		this.issued = issued;
	}
	public Timestamp getReturnDue() {
		return returnDue;
	}
	public void setReturnDue(Timestamp returnDue) {
		this.returnDue = returnDue;
	}
	public Timestamp getReturned() {
		return returned;
	}
	public void setReturned(Timestamp returned) {
		this.returned = returned;
	}
	public Double getFine() {
		return fine;
	}
	public void setFine(Double fine) {
		this.fine = fine;
	}
	@Override
	public String toString() {
		return "IssueRecord [id=" + id + ", copyId=" + copyId + ", memeberId=" + memeberId + ", issued=" + issued
				+ ", returnDue=" + returnDue + ", returned=" + returned + ", fine=" + fine + "]";
	}
}
