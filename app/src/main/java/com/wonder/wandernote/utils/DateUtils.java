package com.wonder.wandernote.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final String RESPONSE_DATE_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String DISPLAY_DATE_FORMAT = "dd MMM yyyy";

    public static String formatCreatedDate(String date) {
        try {
            Date dateTime = new SimpleDateFormat(RESPONSE_DATE_FORMAT, Locale.ENGLISH).parse(date);
            DateFormat dateFormatter = new SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.ENGLISH);
            Date currentDate = new Date();
            long diff = currentDate.getTime() - dateTime.getTime();
            try {
                if (diff < MINUTE_MILLIS) {
                    return "Created Just now";
                } else if (diff < 2 * MINUTE_MILLIS) {
                    return "Created 1 minute ago";
                } else if (diff < 50 * MINUTE_MILLIS) {
                    return diff / MINUTE_MILLIS + " minutes ago";
                } else if (diff < 90 * MINUTE_MILLIS) {
                    return "Created 1 hour ago";
                } else if (diff < 24 * HOUR_MILLIS) {
                    return diff / HOUR_MILLIS + " hours ago";
                } else if (diff < 48 * HOUR_MILLIS) {
                    return "Created 1 day ago";
                } else if (diff < 72 * HOUR_MILLIS) {
                    return "Created 2 days ago";
                } else if (diff < 96 * HOUR_MILLIS) {
                    return "Created 3 days ago";
                } else {
                    return "Created on " + dateFormatter.format(dateTime);
                }
            } catch (Exception e) {
                return "Created on "+date;
            }
        } catch (Exception e) {
            return "Created on "+date;
        }
    }
}
