package com.otostore.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimestampFactory {

	public static Timestamp getCurrenTimestamp(){
		long nanos = 0;
		LocalDateTime date=LocalDateTime.now().minusNanos(nanos);
		return Timestamp.valueOf(date);
	}
}
