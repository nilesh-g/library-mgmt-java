package com.sunbeaminfo.internship.library;

import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.internship.library.entities.Book;
import com.sunbeaminfo.internship.library.entities.BookCopy;
import com.sunbeaminfo.internship.library.entities.DateUtil;
import com.sunbeaminfo.internship.library.entities.IssueRecord;
import com.sunbeaminfo.internship.library.entities.Payment;
import com.sunbeaminfo.internship.library.entities.User;
import com.sunbeaminfo.internship.library.services.BookService;
import com.sunbeaminfo.internship.library.services.IssueRecordService;
import com.sunbeaminfo.internship.library.services.PaymentService;
import com.sunbeaminfo.internship.library.services.UserService;

public class LibraryMain {
	public static Scanner sc;

	public static void ownerArea() {
		int choice;
		do {
			System.out.println("\n\n0. sign out\n1. edit profile\n2. change password\n3. subjectwise copies report\n4. Bookwise copies report\n5. Fees/Fine report\nenter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.out.println("Current user is logged out.");
				return;
			case 1:
				editProfile();
				break;
			case 2:
				changePassword();
				break;
			case 3:
				subjectwiseCopiesReport();
				break;
			case 4:
				bookwiseCopiesReport();
				break;
			case 5:
				dateRangeFeesFineReport();
				break;
			default:
				System.out.println("Invalid option.");
			}
		}while(choice != 0);
	}

	private static void dateRangeFeesFineReport() {
		// TODO Auto-generated method stub
		
	}

	private static void bookwiseCopiesReport() {
		// TODO Auto-generated method stub
		
	}

	private static void subjectwiseCopiesReport() {
		// TODO Auto-generated method stub
		
	}

	private static void changePassword() {
		// TODO Auto-generated method stub
		
	}

	public static void editProfile() {
		
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n\n0. exit\n1. sign in\n2. sign up\nenter choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 0:
				System.out.println("Bye!!");
				break;
			case 1:
				signIn();
				break;
			case 2:
				signUp();
				break;
			default:
				System.out.println("Invalid option.");
			}
		} while(choice != 0);
	}

	private static void signUp() {

	}

	private static void signIn() {
		// TODO Auto-generated method stub

	}

	public static void testMain(String[] args) {
		//		UserService userService = new UserService();
		//		User user = userService.signIn("nilesh@sunbeaminfo.com", "nil");
		//		System.out.println(user);

		//		BookService bookService = new BookService();
		//		List<Book> list = bookService.findBookByName("Program");
		//		list.forEach(System.out::println);

		//		BookService bookService = new BookService();
		//		List<BookCopy> list = bookService.findBookCopyByStatus(2, BookCopy.AVAILABLE);
		//		list.forEach(System.out::println);

		//		IssueRecordService irService = new IssueRecordService();
		//		IssueRecord record = irService.issueBook(2, 3);
		//		System.out.println(record);

		//		IssueRecordService irService = new IssueRecordService();
		//		IssueRecord record = irService.returnBook(5);
		//		System.out.println(record);
		//		if(record.getFine() != null) {
		//			PaymentService payService = new PaymentService();
		//			Payment pay = new Payment(0, record.getMemeberId(), record.getFine(), Payment.FINE, DateUtil.now(), null);
		//			int result = payService.addPayment(pay);
		//			if(result > 0)
		//				System.out.println("Fine is paid: " + record.getFine());
		//		}

		//		IssueRecordService irService = new IssueRecordService();
		//		List<IssueRecord> list = irService.getIssuedBooks(2);
		//		list.forEach(System.out::println);
	}
}
