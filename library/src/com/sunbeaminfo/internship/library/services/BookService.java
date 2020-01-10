package com.sunbeaminfo.internship.library.services;

import java.util.Collections;
import java.util.List;

import com.sunbeaminfo.internship.library.daos.BookDao;
import com.sunbeaminfo.internship.library.daos.Dao;
import com.sunbeaminfo.internship.library.entities.Book;
import com.sunbeaminfo.internship.library.entities.BookCopy;

public class BookService {
	public Book findBookById(int id) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.findBookById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Book> findBookByName(String name) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.findBookByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();		
	}
	
	public int insertBook(Book b) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.insertBook(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateBook(Book b) throws Exception {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.updateBook(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteBook(int id) throws Exception {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.deleteBook(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// ---------------------------------------------------------------------------------------
	
	public BookCopy findBookCopyById(int id) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.findBookCopyById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<BookCopy> findBookCopyByStatus(int bookId, String status) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.findBookCopyByStatus(bookId, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public int insertBookCopy(BookCopy copy) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.insertBookCopy(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateBookCopy(BookCopy copy) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.updateBookCopy(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateBookCopyRack(BookCopy copy, int newRack) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			copy.setRack(newRack);
			return bookDao.updateBookCopy(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateBookCopyStatus(BookCopy copy, String newStatus) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			copy.setStatus(newStatus);
			return bookDao.updateBookCopy(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteBookCopy(int id) {
		try (Dao dao = new Dao()) {
			BookDao bookDao = new BookDao(dao.open());
			return bookDao.deleteBookCopy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}

