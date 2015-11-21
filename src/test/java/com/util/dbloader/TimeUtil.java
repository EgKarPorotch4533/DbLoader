package com.util.dbloader;

import java.util.concurrent.TimeUnit;

public class TimeUtil {

	public static String toHumanPeriod(long milliseconds) {
		StringBuilder sb = new StringBuilder();
		if (milliseconds == 0) {
			sb.append(milliseconds).append("ms");
			return sb.toString();
		}
		long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
		milliseconds -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
		milliseconds -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
		milliseconds -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
		milliseconds -= TimeUnit.SECONDS.toMillis(seconds);
		if (days > 0) {
			sb.append(days).append("d");
		}
		if (hours > 0) {
			sb.append(hours).append("h");
		}
		if (minutes > 0) {
			sb.append(minutes).append("m");
		}
		if (seconds > 0) {
			sb.append(seconds).append("s");
		}
		if (milliseconds > 0) {
			sb.append(milliseconds).append("ms");
		}
		return sb.toString();
	}
}
