package com.sunbeaminfo.internship.library.services;

import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.internship.library.daos.Dao;
import com.sunbeaminfo.internship.library.daos.PaymentDao;
import com.sunbeaminfo.internship.library.entities.Payment;

public class PaymentService {

	public int addPayment(Payment pay) {
		try (Dao dao = new Dao()) {
			PaymentDao payDao = new PaymentDao(dao.open());
			return payDao.insertPayment(pay);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updatePayment(Payment pay) {
		try (Dao dao = new Dao()) {
			PaymentDao payDao = new PaymentDao(dao.open());
			return payDao.updatePayment(pay);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Payment findPaymentById(int paymentId) {
		try (Dao dao = new Dao()) {
			PaymentDao payDao = new PaymentDao(dao.open());
			return payDao.findPaymentById(paymentId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Payment> findPaymentByMember(int memberId, String type) {
		try (Dao dao = new Dao()) {
			PaymentDao payDao = new PaymentDao(dao.open());
			return payDao.findPaymentByMember(memberId, type, false);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Payment>();
	}
	
	public Payment findLastPaymentByMember(int memberId, String type) {
		try (Dao dao = new Dao()) {
			PaymentDao payDao = new PaymentDao(dao.open());
			List<Payment> list = payDao.findPaymentByMember(memberId, type, true);
			if(list.size() > 0)
				return list.get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
