package testFramework.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author B.W.Hart - Alacrity (Pty) Ltd
 */
public final class DateUtils {

    /** Date Format. */
    public static final String FORMAT_DDMMYYYY_DASH = "dd-MM-yyyy";
    /** Date Format. */
    public static final String FORMAT_DDMMYYYY_SLASH = "dd/MM/yyyy";
    /** Date Format. */
    public static final String FORMAT_DDMMYYYY_TIME_DASH = "dd-MM-yyyy hh:mm:ss";
    /** Date Format. */
    public static final String FORMAT_DDMMYYYY_TIME_SLASH = "dd/MM/yyyy hh:mm:ss";
    /** Date Format. */
    public static final String FORMAT_YYYYMMDD_DASH = "yyyy-MM-dd";
    /** Date Format. */
    public static final String FORMAT_YYYYMMDD_SLASH = "yyyy/MM/dd";
    /** Date Format. */
    public static final String FORMAT_YYYYMMDD_TIME_DASH = "yyyy-MM-dd hh:mm:ss";
    /** Date Format. */
    public static final String FORMAT_YYYYMMDD_TIME_DASH_24_HOUR = "yyyy-MM-dd HH:mm:ss";
    /** Date Format. */
    public static final String FORMAT_YYYYMMDD_TIME_SLASH = "yyyy/MM/dd hh:mm:ss";
    /** Namibia Time Zone */
    public static final String NAMIBIA_TIMEZONE = "Africa/Windhoek";

    private DateUtils() {
    }

    public static Boolean isSameDay(Date date1, Date date2) {
        return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
    }

    public static Boolean isSameDay(Calendar cal1, Calendar cal2) {
        return org.apache.commons.lang.time.DateUtils.isSameDay(cal1, cal2);
    }

    /**
     * Main method.
     * 
     * @param args arguments
     */
    public static void main(String[] args) {
        Date dob = date(1980, 03, 15);
        Date today = new Date();
        //System.out.println("x=" + x(dob, today));
        //System.out.println("x+1=" + (x(dob, today) + 1));
        System.out.println("age=" + calculateAge(dob, today));
        System.out.println("alb=" + calculateAgeALB(dob, today));
        System.out.println("anb=" + calculateAgeANB(dob, today));

//        System.out.println("DATE=" + date(2010, 11, 25));
    }

    /**
     * Converts a Date object to an XMLGregorianCalendar object.
     * 
     * @param date the Date object to convert
     * @return XMLGregorianCalendar object
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar toXmlGregorian(Date date) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        return xmlDate;
    }

    /**
     * Converts a Timestamp object to an XMLGregorianCalendar object.
     * 
     * @param ts the Date object to convert
     * @return XMLGregorianCalendar object
     * @throws DatatypeConfigurationException
     */
    public static XMLGregorianCalendar toXmlGregorian(Timestamp ts) throws DatatypeConfigurationException {
        return toXmlGregorian(new Date(ts.getTime()));
    }

    /**
     * Returns the current day.
     * 
     * @return the current day
     */
    public static Timestamp getToday() {
        return ts(new Date());
    }

    /**
     * Returns the current day, but with a zero time.
     * 
     * @return the current day
     */
    public static Timestamp getTodayZeroTime() {
        Date date = getToday();
        return ts(setTimeToZero(date));
    }

    /**
     * Sets the time part of a date to zero.
     * 
     * @param date the date to modify
     * @return the modified date
     */
    public static Timestamp setTimeToZero(Date date) {
        return ts(DateUtils.setTimeToZero(date));
    }

    /**
     * Conversion from a Date to a Timestamp.
     * 
     * @param date the Date parameter
     * @return the equivalent Timestamp 
     */
    public static Timestamp ts(Date date) {
        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * Conversion from a Timestamp to a Date.
     * 
     * @param ts the Timestamp parameter
     * @return the equivalent Date 
     */
    public static Date date(Timestamp ts) {
        return new Date(ts.getTime());
    }

    /**
     * Parses a String to a Date.
     *
     * @param s the String to parse
     * @param format the date format
     * @return a Date object
     * @throws ParseException if the parse operation was unsuccessful
     */
    public static Date parse(String s, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(s);
        return d;
    }

    /**
     * Parses a String to a Date, using default format.
     *
     * @param s the String to parse
     * @return a Date object
     * @throws ParseException if the parse operation was unsuccessful
     */
    public static Date parse(String s) throws ParseException {
        return parse(s, FORMAT_DDMMYYYY_SLASH);
    }

    /**
     * Traditional Date() constructor.
     *
     * @param yy year (2010 or similar)
     * @param mm month (1..12)
     * @param dd day (1..31)
     * @return a Date object
     */
    public static Date date(Integer yy, Integer mm, Integer dd) {
        //Integer minYear = 1900;
        Integer minYear = 1800;
        Integer maxYear = 2099;
        if (yy == null || yy < minYear || yy > maxYear) {
            throw new IllegalArgumentException("Invalid year provided [" + yy + "] - must be (" + minYear + ".." + maxYear + ")!");
        }
        if (mm == null || mm < 1 || mm > 12) {
            throw new IllegalArgumentException("Invalid month provided [" + mm + "] - must be (1..12)!");
        }
        if (dd == null || dd < 1 || dd > 31) {
            throw new IllegalArgumentException("Invalid day provided [" + dd + "] - must be (1..31)!");
        }

        try {
            //System.out.println("yy=" + yy + ";mm=" + mm + ";dd=" + dd);
            Calendar cal = GregorianCalendar.getInstance();
            cal.setLenient(false);
            cal.set(yy, mm - 1, dd, 0, 0, 0);// Calendar requires a 0-based month
            cal.set(Calendar.MILLISECOND, 0);
            Date dte = cal.getTime();
            return dte;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid Date provided [" + yy + "/" + mm + "/" + dd + "]!");
        }
    }

//    /**
//     * Calculates the age between two dates.
//     *
//     * @param dob the first date
//     * @param current the second date
//     * @return age in decimal years
//     */
//    public static Double calculateAge(Date dob, Date current) {
//
//        Calendar birthday = Calendar.getInstance();
//        Calendar today = Calendar.getInstance();
//
//        birthday.setTime(dob);
//        long milis1 = birthday.getTimeInMillis();
//        long milis2 = today.getTimeInMillis();
//        long diff = milis2 - milis1;
//        long diffDays = diff / (24 * 60 * 60 * 1000);
//        System.out.println("diffdays=" + diffDays);
//        Double diffYears = diffDays / 365.25;
//
//        return diffYears;
//    }
    /**
     * Calculates the age (at last birthday) between two dates.
     *
     * @param dob the first date
     * @param current the second date
     * @return age at last birthday
     */
    public static Integer calculateAgeALB(Date dob, Date current) {
//        Long age = (long) Math.floor(calculateAge(dob, current));
//        return age.intValue();
        return calculateAge(dob, current);
    }

    /**
     * Calculates the age (at last birthday) between two dates.
     *
     * @param dob the first date
     * @param current the second date
     * @return age at last birthday
     */
    public static Integer calculateAge(Date dob, Date current) {

        Integer[] dobarr = breakDate(dob);
        Integer[] nowarr = breakDate(current);

        Integer age = nowarr[2] - dobarr[2];
        if (nowarr[1] < dobarr[1]) {
            age -= 1;
        }
        if (nowarr[1] == dobarr[1] && nowarr[0] < dobarr[0]) {
            age -= 1;
        }

        return age;
    }

    /**
     * Calculates the age (at last birthday) between two dates.
     *
     * @param dob the first date
     * @param current the second date
     * @return age at last birthday
     */
    public static Integer calculateAgeANB(Date dob, Date current) {
        return calculateAge(dob, current) + 1;
    }

    /**
     * Formats a timestamp to a String.
     *
     * @param ts the timestamp to format
     * @param format the format to apply
     * @return the formatted String
     */
    public static String formatDate(Timestamp ts, String format) {
        return formatDate(date(ts), format);
    }

    /**
     * Formats a timestamp to a String, using default format.
     *
     * @param ts the timestamp to format
     * @return the formatted String
     */
    public static String formatDate(Timestamp ts) {
        return formatDate(date(ts), FORMAT_DDMMYYYY_SLASH);
    }

    /**
     * Formats a Date to a String, using default format.
     *
     * @param date the date to format
     * @return the formatted String
     */
    public static String formatDate(Date date) {
        return formatDate(date, FORMAT_DDMMYYYY_SLASH);
    }

    /**
     * Formats a Date to a String.
     * 
     * @param date the timestamp to format
     * @param format the format to apply
     * @return the formatted String
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            throw new IllegalArgumentException("[Date] parameter is compulsory!");
        }
        if (StringUtils.absent(format)) {
            throw new IllegalArgumentException("[Format] parameter is compulsory!");
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    /**
     * Extracts the year part from a timestamp.
     *
     * @param ts the timestamp
     * @return the year part
     */
    public static Integer extractYear(Timestamp ts) {
        Integer[] arr = breakDate(ts);
        return arr[2];
    }

    /**
     * Extracts the month part from a timestamp.
     *
     * @param ts the timestamp
     * @return the month part
     */
    public static Integer extractMonth(Timestamp ts) {
        Integer[] arr = breakDate(ts);
        return arr[1];
    }

    /**
     * Extracts the day part from a timestamp.
     *
     * @param ts the timestamp
     * @return the day part
     */
    public static Integer extractDay(Timestamp ts) {
        Integer[] arr = breakDate(ts);
        return arr[0];
    }

    /**
     * Break the date and return an int array holding values DD, MM, YYYY.
     *
     * @param date the date to break up
     * @return int[] the split values
     */
    public static Integer[] breakDate(Date date) {
        return breakDate(new Timestamp(date.getTime()));
    }

    /**
     * Break the date and return an int array holding values DD, MM, YYYY.
     *
     * @param date the date to break up
     * @return int[] the split values
     */
    public static Integer[] breakDate(Timestamp date) {
        if (date == null) {
            throw new IllegalArgumentException("Timestamp parameter is compulsory!");
        }

        Integer[] arrDate = new Integer[3];
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTimeInMillis(date.getTime());
        int intDate = gCalendar.get(Calendar.DATE);
        int intMonth = gCalendar.get(Calendar.MONTH);
        int intYear = gCalendar.get(Calendar.YEAR);
        arrDate[0] = intDate;
        arrDate[1] = intMonth + 1;
        arrDate[2] = intYear;
        return arrDate;
    }

    /**
     * Calculates leap year.
     *
     * @param year the year
     * @return array containing number of days per month
     */
    public static Integer[] calLeap(Integer year) {
        if (year == null || year < 1900 || year > 2050) {
            throw new IllegalArgumentException("Invalid year parameter [" + year + "]!");
        }

        Integer[] arrMnthDays = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        };

        GregorianCalendar gCalendar = new GregorianCalendar();
        if (gCalendar.isLeapYear(year)) {
            arrMnthDays[1] = 29;
        }
        return arrMnthDays;
    }

    /**
     * Adds a number of months to the provided date.
     *
     * @param ts the date to add months to
     * @param months the number of months to add
     * @return the new date
     */
    public static Timestamp addMonths(Timestamp ts, Integer months) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(ts.getTime());
        cal1.add(Calendar.MONTH, months);
        return new Timestamp(cal1.getTimeInMillis());
    }

    /**
     * differences of two dates in terms of Number of Days.
     *
     * @param ts1 first date
     * @param ts2 second date
     * @return number of days between two dates
     */
    public static Integer daysBetween(Timestamp ts1, Timestamp ts2) {
        int intNoOfDays = 0;
        Integer returnValue = 0;
        long lngDays;

        if (ts1 != null || ts2 != null) {

            long lngDateMillis1;
            long lngDateMillis2;

            if (ts1.after(ts2)) {
                lngDateMillis1 = ts1.getTime();
                lngDateMillis2 = ts2.getTime();
            } else {
                lngDateMillis1 = ts2.getTime();
                lngDateMillis2 = ts1.getTime();
            }
            lngDays = (lngDateMillis1 - lngDateMillis2);
            lngDays = (((lngDays / 1000) / 60) / 60) / 24;
            intNoOfDays = (int) lngDays;
            returnValue = intNoOfDays;

        }
        return returnValue;
    }

    /**
     * differences of two dates in terms of number of months.
     *
     * @param top first date
     * @param bottom second date
     * @return number of months between two dates
     */
    public static Integer monthsBetween(Timestamp top, Timestamp bottom) {
        Integer diffInMonths = 0;

        Calendar topDate = GregorianCalendar.getInstance();
        topDate.setTimeInMillis(top.getTime());
        Calendar bottomDate = GregorianCalendar.getInstance();
        bottomDate.setTimeInMillis(bottom.getTime());

        int tYear = topDate.get(Calendar.YEAR);
        int tMonth = (topDate.get(Calendar.MONTH));
        int tDay = topDate.get(Calendar.DAY_OF_MONTH);
        int tTotMonths = 0;

        int bYear = bottomDate.get(Calendar.YEAR);
        int bMonth = (bottomDate.get(Calendar.MONTH));
        int bDay = bottomDate.get(Calendar.DAY_OF_MONTH);
        int bTotMonths = 0;

        // calculate the amount of months between 1900 and byear
        for (int i = 1900; i < tYear; i++) {
            tTotMonths += 12;        // calculate the remaining months
        }
        for (int i = 0; i < tMonth; i++) {
            tTotMonths++;        // calculate the amount of months between 1900 and byear
        }
        for (int i = 1900; i < bYear; i++) {
            bTotMonths += 12;        // calculate the remaining months
        }
        for (int i = 0; i < bMonth; i++) {
            bTotMonths++;
        }
        diffInMonths = tTotMonths - bTotMonths;

        if (bDay < tDay) {
            diffInMonths++;
        }
        return diffInMonths;
    }

    /**
     * Returns the earlier of two dates.
     *
     * @param d1 first date
     * @param d2 second date
     * @return the earlier of the two dates
     */
    public static Date getEarlierDate(Date d1, Date d2) {
        if (d1.after(d2)) {
            return d2;
        } else {
            return d1;
        }
    }

    /**
     * Returns the later of two dates.
     *
     * @param d1 first date
     * @param d2 second date
     * @return the later of the two dates
     */
    public static Date getLaterDate(Date d1, Date d2) {
        if (d1.after(d2)) {
            return d1;
        } else {
            return d2;
        }
    }

    /**
     * Returns the number of years between start date and end date EXCLUDING start and end dates.
     *
     * @param ts1 first date
     * @param ts2 second date
     * @return the number of years
     */
    public static Integer getYearDelta(Timestamp ts1, Timestamp ts2) {
        Calendar c1 = GregorianCalendar.getInstance();
        Calendar c2 = GregorianCalendar.getInstance();

        int tmpDelta = 0;
        Date d1 = new Date(ts1.getTime());
        Date d2 = new Date(ts2.getTime());

        c1.setTime(getEarlierDate(d1, d2));
        c2.setTime(getLaterDate(d1, d2));
        tmpDelta = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        // increment c1 and test: back off if we've passed the target date
        c1.add(Calendar.YEAR, tmpDelta);
        if ((c1.getTime()).getTime() > (c2.getTime()).getTime()) {
            return (tmpDelta - 1);
        } else {
            return (tmpDelta);
        }
    }

    public static Timestamp getFirstDayOfMonth(Timestamp ts) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        cal.set(Calendar.DATE, 1);
        Date d = cal.getTime();
        return setTimeToZero(d);
    }

    public static Timestamp getLastDayOfMonth(Timestamp ts) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        cal.set(Calendar.DATE, getNoOfDaysInMonth(ts));
        Date d = cal.getTime();
        return setTimeToZero(d);
    }

    public static Date getFirstDayOfMonth(Date date) {
        return getFirstDayOfMonth(ts(date));
    }

    public static Date getLastDayOfMonth(Date date) {
        return getLastDayOfMonth(ts(date));
    }

    /**
     * Return number of days in a month.
     *
     * @param ts the date of the month to evaluate
     * @return number of days in that month
     */
    public static int getNoOfDaysInMonth(Timestamp ts) {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] monthsLeap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(ts.getTime());
        int month = cal1.get(Calendar.MONTH);
        int dayOfMonth = cal1.get(Calendar.DAY_OF_MONTH);
        int year = cal1.get(Calendar.YEAR);

        if (year % 4 == 0
                || year % 100 == 0
                || year % 400 == 0) {
            dayOfMonth = monthsLeap[month];
            cal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        } else {
            dayOfMonth = months[month];
            cal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }

        return dayOfMonth;
    }

    /**
     * Subtract days from provided date.
     *
     * @param ts the date to manipulate
     * @param numDays number of days to subtract
     * @return the modified date
     * @deprecated
     */
    public static Timestamp subDays(Timestamp ts, int numDays) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(ts.getTime());
        cal1.add(Calendar.DAY_OF_MONTH, (numDays * -1));
        return new Timestamp(cal1.getTimeInMillis());
    }

    /**
     * Subtract months from current month if the day is the last day of the month
     * set the calculated month as the last day of the month.
     *
     * @param ts date to manipulate
     * @param numMths number of months to subtract
     * @return the modified date
     * @deprecated
     */
    public static Timestamp subMonths(Timestamp ts, int numMths) {
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] monthsLeap = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(ts.getTime());
        int month = cal1.get(Calendar.MONTH);
        int dayOfMonth = cal1.get(Calendar.DAY_OF_MONTH);
        int year = cal1.get(Calendar.YEAR);
        boolean lastDayOfMonth = false;
        int endOfMonth = 0;
        if (year % 4 == 0
                || year % 100 != 0
                || year % 400 == 0) {
            endOfMonth = monthsLeap[month];
        } else {
            endOfMonth = months[month];
        }
        if (endOfMonth == dayOfMonth) {
            lastDayOfMonth = true;
        }
        cal1.add(Calendar.MONTH, (numMths * -1));
        if (lastDayOfMonth) {
            if (year % 4 == 0
                    || year % 100 != 0
                    || year % 400 == 0) {
                month = cal1.get(Calendar.MONTH);
                dayOfMonth = monthsLeap[month];
                cal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            } else {
                month = cal1.get(Calendar.MONTH);
                dayOfMonth = months[month];
                cal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        }
        return new Timestamp(cal1.getTimeInMillis());
    }

    /**
     * Add days to current date.
     *
     * @param ts the date to manipulate
     * @param numDays number of days to add
     * @return the modified date
     */
    public static Timestamp addDays(Timestamp ts, int numDays) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(ts.getTime());
        cal1.add(Calendar.DAY_OF_MONTH, (numDays));
        return new Timestamp(cal1.getTimeInMillis());
    }

    /**
     * Add years to current date.
     *
     * @param ts the date to manipulate
     * @param numYrs number of years to add
     * @return the modified date
     */
    public static Timestamp addYears(Timestamp ts, int numYrs) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(ts.getTime());
        cal1.add(Calendar.YEAR, numYrs);
        return new Timestamp(cal1.getTimeInMillis());
    }

    /**
     * Determines YYYYMM value from a Date.
     * 
     * @param date the date
     * @return the YYYYMM value
     */
    public static Integer getYearMonthValue(Date date) {
        return getYearMonthValue(ts(date));
    }

    /**
     * Determines YYYYMM value from a Timestamp.
     * 
     * @param ts the timestamp
     * @return the YYYYMM value
     */
    public static Integer getYearMonthValue(Timestamp ts) {
        Integer n = extractYear(ts) * 100;
        n += extractMonth(ts);
        return n;
    }
    
    public static Date getNamibiaTime() {
        TimeZone nam = TimeZone.getTimeZone(NAMIBIA_TIMEZONE);
        Calendar cal = Calendar.getInstance(nam);
        Date namDate = new Date(cal.getTimeInMillis());
        return namDate;
    }
    
     public static Date getNamibiaTimeZeroTime() {
        Date namDate = getNamibiaTime();
        return ts(setTimeToZero(namDate));
    }
//    /**
//     * Validate date entry for first day of the month.
//     *
//     * @param date
//     * @param finYr
//     * @return
//     */
//    public static Boolean isFirstDayOfMonth(Timestamp date, Integer finYr) {
//        int intDate = 0;
//        int intFinYr = 0;
//        int intYear = 0;
//        Boolean isValid = true;
//
//        GregorianCalendar greCalender = new GregorianCalendar();
//        greCalender.setTimeInMillis(date.getTime());
//
//        intDate = greCalender.get(Calendar.DATE);
//        if (intDate != 1) {
//            isValid = false;
//        }
//
//        intYear = greCalender.get(Calendar.YEAR);
//        intFinYr = finYr;
//        if (intYear != intFinYr) {
//            isValid = false;
//        }
//        return isValid;
//    }
///**
//     * Determines the number of days between two dates.
//     *
//     * @param ts1 the first date
//     * @param ts2 the second date
//     * @return number of days
//     */
//    public static Integer differenceOfDates(Timestamp ts1, Timestamp ts2) {
//        if (ts1 == null || ts2 == null) {
//            throw new IllegalArgumentException("Both date parameters are compulsory!");
//        }
//
//        Integer intReturnValue = 0;
//        Long lngDays = 0L;
//        long lngDateMillis1;
//        long lngDateMillis2;
//
//        if (ts1.after(ts2)) {
//            lngDateMillis1 = ts1.getTime();
//            lngDateMillis2 = ts2.getTime();
//        } else {
//            lngDateMillis1 = ts2.getTime();
//            lngDateMillis2 = ts1.getTime();
//        }
//        lngDays = (lngDateMillis1 - lngDateMillis2);
//        lngDays = (((lngDays / 1000) / 60) / 60) / 24;
//        intReturnValue = lngDays.intValue();
//        return intReturnValue;
//    }
//    /**
//     * Adds noOfDays from the input Date and returns result in TimeStamp format.
//     *
//     * @param ts the date to add days to
//     * @param noOfDays number of days to add (can be negative)
//     * @return the new date
//     * @deprecated use addDays()
//     */
//    public static Timestamp dateSum(Timestamp ts, int noOfDays) {
//        if (ts == null) {
//            throw new IllegalArgumentException("Date parameter is compulsory!");
//        }
//
//        Timestamp tspReturnValue = Timestamp.valueOf("0001-01-01 00:00:00.000000000");
//        long lngDateMillis = ts.getTime();
//        long lngDayInMillis = 60 * 60 * 24 * noOfDays;
//        lngDateMillis += (lngDayInMillis * 1000);
//        tspReturnValue.setTime(lngDateMillis);
//        return tspReturnValue;
//    }
}
