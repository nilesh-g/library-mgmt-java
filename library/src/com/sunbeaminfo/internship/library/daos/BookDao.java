package com.sunbeaminfo.internship.library.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.internship.library.entities.Book;
import com.sunbeaminfo.internship.library.entities.BookCopy;

public class BookDao {
	private Connection con;
	
	public BookDao(Connection con) {
		this.con = con;
	}
	
	public Book findBookById(int id) throws Exception {
		String sql = "SELECT id, name, author, subject, price, isbn FROM books WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					Book b = new Book();
					b.setId( rs.getInt("id") );
					b.setName( rs.getString("name") );
					b.setAuthor( rs.getString("author") );
					b.setSubject( rs.getString("subject") );
					b.setPrice( rs.getDouble("price") );
					b.setIsbn( rs.getString("isbn") );
					return b;
				}
			}
			return null;
		}
	}
	
	public List<Book> findBookByName(String name) throws Exception {
		String sql = "SELECT id, name, author, subject, price, isbn FROM books WHERE name LIKE ?";
		List<Book> list = new ArrayList<Book>();
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, "%"+name+"%");
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					Book b = new Book();
					b.setId( rs.getInt("id") );
					b.setName( rs.getString("name") );
					b.setAuthor( rs.getString("author") );
					b.setSubject( rs.getString("subject") );
					b.setPrice( rs.getDouble("price") );
					b.setIsbn( rs.getString("isbn") );
					list.add(b);
				}
			}
		}
		return list;
	}
	
	public int insertBook(Book b) throws Exception {
		String sql = "INSERT INTO books(name, author, subject, price, isbn) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, b.getName());
			stmt.setString(2, b.getAuthor());
			stmt.setString(3, b.getSubject());
			stmt.setDouble(4, b.getPrice());
			stmt.setString(5, b.getIsbn());
			return stmt.executeUpdate();
		}
	}
	
	public int updateBook(Book b) throws Exception {
		String sql = "UPDATE books SET name=?, author=?, subject=?, price=?, isbn=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, b.getName());
			stmt.setString(2, b.getAuthor());
			stmt.setString(3, b.getSubject());
			stmt.setDouble(4, b.getPrice());
			stmt.setString(5, b.getIsbn());
			stmt.setInt(6, b.getId());
			return stmt.executeUpdate();
		}
	}
	
	public int deleteBook(int id) throws Exception {
		String sql = "DELETE FROM books WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		}
	}
	
	//---------------------------------------------------------------------------------------
	
	public BookCopy findBookCopyById(int id) throws Exception {
		String sql = "SELECT id, bookid, rack, status FROM copies WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					BookCopy copy = new BookCopy();
					copy.setId( rs.getInt("id") );
					copy.setBookId( rs.getInt("bookid") );
					copy.setRack( rs.getInt("rack") );
					copy.setStatus( rs.getString("status") );
					return copy;
				}
			}
			return null;
		}
	}
	
	public List<BookCopy> findBookCopyByStatus(int bookId, String status) throws Exception {
		String sql = "SELECT id, bookid, rack, status FROM copies WHERE status=?";
		if(bookId != 0)
			sql += " AND bookid=?";
		List<BookCopy> list = new ArrayList<BookCopy>();
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, status);
			if(bookId != 0)
				stmt.setInt(2, bookId);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					BookCopy copy = new BookCopy();
					copy.setId( rs.getInt("id") );
					copy.setBookId( rs.getInt("bookid") );
					copy.setRack( rs.getInt("rack") );
					copy.setStatus( rs.getString("status") );
					list.add(copy);
				}
			}
		}
		return list;
	}
	
	public int insertBookCopy(BookCopy copy) throws Exception {
		String sql = "INSERT INTO copies(bookid, rack, status) VALUES (?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, copy.getBookId());
			stmt.setInt(2, copy.getRack());
			stmt.setString(3, copy.getStatus());
			return stmt.executeUpdate();
		}
	}
	
	public int updateBookCopy(BookCopy copy) throws Exception {
		String sql = "UPDATE copies SET bookid=?, rack=?, status=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, copy.getBookId());
			stmt.setInt(2, copy.getRack());
			stmt.setString(3, copy.getStatus());
			stmt.setInt(4, copy.getId());
			return stmt.executeUpdate();
		}
	}
	
	public int deleteBookCopy(int id) throws Exception {
		String sql = "DELETE FROM copies WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		}
	}
}

