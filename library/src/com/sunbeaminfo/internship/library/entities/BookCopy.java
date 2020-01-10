package com.sunbeaminfo.internship.library.entities;

public class BookCopy {
	public static final String AVAILABLE = "available";
	public static final String ISSUED = "issued";
	public static final String LOST = "lost";
	
	private int id;
	private int bookId;
	private int rack;
	private String status;
	
	public BookCopy() {
		this(0, 0, 0, AVAILABLE);
	}

	public BookCopy(int id, int bookId, int rack, String status) {
		this.id = id;
		this.bookId = bookId;
		this.rack = rack;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getRack() {
		return rack;
	}

	public void setRack(int rack) {
		this.rack = rack;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookCopy [id=" + id + ", bookId=" + bookId + ", rack=" + rack + ", status=" + status + "]";
	}
}
