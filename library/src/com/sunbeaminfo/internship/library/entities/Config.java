package com.sunbeaminfo.internship.library.entities;

public class Config {
	public static final String APP_NAME = "Library Management";
	public static final int RACK_COUNT = 8;
	public static final int MAX_ISSUE_COUNT = 2;
	public static final double FINE_PER_DAY = 5.00;
	public static final int BORROW_DURATION_DAYS = 7;
	public static final long BORROW_DURATION_MILLIS = BORROW_DURATION_DAYS * 24L * 60 * 60 * 1000;
}
