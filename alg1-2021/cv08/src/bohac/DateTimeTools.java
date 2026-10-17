package bohac;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.regex.Pattern;

/**
 * nikde jsem se nedocetl, jak to do tech funkci pumpovat, jestli jako string nebo jako int argumenty, dont judge
 */
public class DateTimeTools {
    public static void main(String[] args) {
    }

    public static boolean leapYear(long year) {
        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    /**
     * Date validator
     *
     * @param date Date in format dd/mm/yyyy or dd.mm.yyyy
     * @return boolean valid
     */
    public static boolean dateValid(String date) {
        Pattern pattern = Pattern.compile("^(?:31([/\\-.])(?:0?[13578]|1[02])\\1|(?:29|30)([/\\-.])(?:0?[1,3-9]|1[0-2])\\2)(?:1[6-9]|[2-9]\\d)?\\d{2}$|^29([/\\-.])0?2\\3(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$|^(?:0?[1-9]|1\\d|2[0-8])([/\\-.])(?:0?[1-9]|1[0-2])\\4(?:1[6-9]|[2-9]\\d)?\\d{2}$");
        return pattern.matcher(date).matches();
    }


    private static boolean range(int value, int high) {
        return value > 0 && value < high;
    }

    public static boolean isValidTime(int hours, int minutes, int seconds) {
        return (range(hours, 23) && range(minutes, 59) && range(seconds, 59));
    }

    /**
     * Time validator
     *
     * @param time  Time in 24h or 12h format (hh:mm:ss)
     * @param use24 Use 24h format (if false, 12h format will be used)
     * @return boolean valid
     */
    public static boolean timeValid(String time, boolean use24) {
        Pattern _24 = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9]):([0-5]?[0-9])$");
        Pattern _12 = Pattern.compile("^(?:1[0-2]|0?[0-9]):[0-5][0-9]:[0-5][0-9]$");
        return use24 ? _24.matcher(time).matches() : _12.matcher(time).matches();
    }

    /**
     * pocitam s tim, ze date je valid
     */
    public static String dayOfWeek(int year, int month, int day) {
        int k = year % 100;
        int j = year / 100;
        return DayOfWeek.of((((((day + (((month + 1) * 26) / 10) + k + (k / 4) + (j / 4)) + (5 * j)) % 7) + 5) % 7) + 1).name();
    }

    /**
     * prakticky cely je to custom, nechtelo se mi delat extra array, to se nepocita jako api xd
     */
    public static int daysInMonth(int month, int year) {
        return Month.values()[month - 1].length(leapYear(year));
    }
}
