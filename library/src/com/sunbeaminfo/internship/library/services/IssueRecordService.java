package com.sunbeaminfo.internship.library.services;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.internship.library.daos.BookDao;
import com.sunbeaminfo.internship.library.daos.Dao;
import com.sunbeaminfo.internship.library.daos.IssueRecordDao;
import com.sunbeaminfo.internship.library.entities.BookCopy;
import com.sunbeaminfo.internship.library.entities.Config;
import com.sunbeaminfo.internship.library.entities.DateUtil;
import com.sunbeaminfo.internship.library.entities.IssueRecord;
import com.sunbeaminfo.internship.library.entities.Payment;

public class IssueRecordService {

	public IssueRecord issueBook(int bookId, int memberId) {
		PaymentService payService = new PaymentService();
		if(payService.findLastPaymentByMember(memberId, Payment.FEE) == null)
			throw new RuntimeException("User is not paid.");
		
		IssueRecord record = null;
		try (Dao dao = new Dao()) {
			Connection con = dao.openTx();
						
			BookDao bookDao = new BookDao(con);
			List<BookCopy> copies = bookDao.findBookCopyByStatus(bookId, BookCopy.AVAILABLE);
			if(copies.size() == 0)
				throw new RuntimeException("Book is not available.");
			
			BookCopy copy = copies.get(0);
			copy.setStatus(BookCopy.ISSUED);
			bookDao.updateBookCopy(copy);
			
			IssueRecordDao irDao = new IssueRecordDao(con);
			record = new IssueRecord(0, copy.getId(), memberId, 
					DateUtil.now(), DateUtil.addDays(DateUtil.now(), Config.BORROW_DURATION_DAYS), 
					null, null);
			irDao.insertIssueRecord(record);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}
	
	public IssueRecord findIssueRecordById(int issueRecordId) {
		try (Dao dao = new Dao()) {
			IssueRecordDao irDao = new IssueRecordDao(dao.open());
			return irDao.findIssueRecordById(issueRecordId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssueRecord returnBook(int issueRecordId) {
		IssueRecord record = null;
		try (Dao dao = new Dao()) {
			Connection con = dao.openTx();
			
			IssueRecordDao irDao = new IssueRecordDao(con);
			record = irDao.findIssueRecordById(issueRecordId);
			if(record == null)
				throw new RuntimeException("IssueRecord not found.");
			if(record.getReturned() != null)
				throw new RuntimeException("Book is already returned.");
			
			Timestamp returned = DateUtil.now();
			record.setReturned(returned);
			if(returned.after(record.getReturnDue())) {
				int lateDays = DateUtil.dateDiff(returned, record.getReturnDue());
				double fine = lateDays * Config.FINE_PER_DAY;
				record.setFine(fine);
			}
			irDao.updateIssueRecord(record);
			
			BookDao bookDao = new BookDao(con);
			BookCopy copy = bookDao.findBookCopyById(record.getCopyId());
			copy.setStatus(BookCopy.AVAILABLE);
			bookDao.updateBookCopy(copy);
			
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}
	
	public List<IssueRecord> getIssuedBooks(int memberId) {
		try (Dao dao = new Dao()) {
			IssueRecordDao irDao = new IssueRecordDao(dao.open());
			return irDao.findIssueRecordByMember(0, IssueRecord.ISSUED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<IssueRecord>();
	}
}
