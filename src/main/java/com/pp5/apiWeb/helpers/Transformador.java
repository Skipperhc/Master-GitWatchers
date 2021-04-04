package com.pp5.apiWeb.helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Transformador {

	public static LocalDate transformarUnixLocalDate(Long unix_seconds) {
//		long unix_seconds = 1573344000;
//		// convert seconds to milliseconds
//		Date date = new Date(unix_seconds * 1000L);
//		// format of the date
//		SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//		jdf.setTimeZone(TimeZone.getTimeZone("GMT-0"));
//		String java_date = jdf.format(date);
//		System.out.println("\n" + java_date + "\n");
//		
//		LocalDate dateToLocalDate = date.toInstant().atZone(ZoneId.of("Etc/UTC")).toLocalDate();
//		System.out.println(dateToLocalDate);
//
//		long timeInSeconds = 1573344000;
//		LocalDate localDate5 = LocalDate.ofInstant(Instant.ofEpochSecond(timeInSeconds), ZoneId.of("Etc/UTC"));
//		System.out.println(localDate5);
		
		return new Date(unix_seconds * 1000L).toInstant().atZone(ZoneId.of("Etc/UTC")).toLocalDate();
	}
	
}
