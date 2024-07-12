package com.thatmg393.android.ts4j.log;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {
	private final Date date = new Date();
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy H:mm:ss zzz", Locale.getDefault());
	
	@Override
	public String format(LogRecord logRecord) {
		date.setTime(logRecord.getMillis());
		
		return ZonedDateTime.now(ZoneId.systemDefault()).format(dateFormatter) +
		" " + logRecord.getLoggerName() +
		":" + logRecord.getLevel().getLocalizedName() +
		" | " + formatMessage(logRecord);
	}

	@Override
	public String getHead(Handler handler) {
		return "Using com.thatmg393.android.ts4j.log.CustomLogFormatter.java";
	}
}
