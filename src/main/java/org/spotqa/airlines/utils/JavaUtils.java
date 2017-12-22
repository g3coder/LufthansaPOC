package org.spotqa.airlines.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lakkaraju on 21-12-2017.
 */
public class JavaUtils {

    public static int selectFutureDate(int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

}



