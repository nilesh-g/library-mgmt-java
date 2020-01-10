package com.sunbeaminfo.internship.library.entities;

import java.sql.Timestamp;

public class Payment {
	public static final String FEE = "fee";
	public static final String FINE = "fine";
	
	private int id;
	private int memberId;
	private double amount;
	private String type;
	private Timestamp txTime;
	private Timestamp dueDate;
	
	public Payment() {
		this(0, 0, 0.0, FINE, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + Config.BORROW_DURATION_MILLIS));
	}

	public Payment(int id, int memberId, double amount, String type, Timestamp txTime, Timestamp dueDate) {
		this.id = id;
		this.memberId = memberId;
		this.amount = amount;
		this.type = type;
		this.txTime = txTime;
		this.dueDate = dueDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getTxTime() {
		return txTime;
	}

	public void setTxTime(Timestamp txTime) {
		this.txTime = txTime;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", memberId=" + memberId + ", amount=" + amount + ", type=" + type + ", txTime="
				+ txTime + ", dueDate=" + dueDate + "]";
	}
}
