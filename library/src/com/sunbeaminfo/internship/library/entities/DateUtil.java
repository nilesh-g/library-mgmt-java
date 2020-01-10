package com.sunbeaminfo.internship.library.entities;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
	public static Timestamp now() {
		return new Timestamp(new Date().getTime());
	}
	
	public static Timestamp addDays(Timestamp time, int days) {
		long ms = days * 24L * 60 * 60 * 1000;
		return new Timestamp(time.getTime() + ms);
	}
	
	public static int dateDiff(Timestamp t1, Timestamp t2) {
		long diff = Math.abs(t1.getTime() - t2.getTime());
		long ms = 1 * 24L * 60 * 60 * 1000;
		return (int)Math.ceil((double)diff / ms);
	}
}
