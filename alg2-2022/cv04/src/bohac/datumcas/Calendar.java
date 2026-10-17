package bohac.datumcas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;

import static java.time.temporal.ChronoField.*;

public class Calendar {
    private int day, month, year, monthLength;

    private Calendar(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.monthLength = YearMonth.of(year, month).lengthOfMonth();
    }

    private Calendar(LocalDate date) {
        this(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    public static Calendar of(int day, int month, int year) {
        YEAR.checkValidValue(year);
        MONTH_OF_YEAR.checkValidValue(month);
        DAY_OF_MONTH.checkValidValue(day);
        return new Calendar(day, month, year);
    }

    public static Calendar now() {
        return new Calendar(LocalDate.now());
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    /**
     * Sosnul jsem si z Java source kodu
     *
     * @param year year value
     * @return boolean
     */
    public static boolean isLeap(int year) {
        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    /**
     * Pretty self explanatory
     *
     * @param year year value
     * @return 365/366 depending on leap year condition
     */
    public static int daysInYear(int year) {
        return isLeap(year) ? 366 : 365;
    }

    /**
     * Zelleruv algoritmus - special thanks to gfg
     *
     * @return (1 = Monday to 7 = Sunday)
     */
    public static int weekDayIndex(int day, int month, int year) {
        if (month < 3) {
            month += 12;
            year--;
        }
        int k = year % 100;
        int j = year / 100;
        int h = day + 13 * (month + 1) / 5 + k + k / 4 + j / 4 + 5 * j;
        return ((h + 5) % 7) + 1;
    }

    /**
     * The same as {@link #weekDayIndex(int, int, int)} except it returns DayOfWeek enum
     *
     * @return DayOfWeek enum
     */
    public static DayOfWeek weekDay(int day, int month, int year) {
        return DayOfWeek.of(weekDayIndex(day, month, year));
    }

    /**
     * Calendar visual representation of the current month
     *
     * @return string representation
     */
    public String monthCalendar() {
        return monthHeader() + System.getProperty("line.separator") + monthBody() + System.getProperty("line.separator");
    }

    public void nextDay() {
        if (day == monthLength) {
            if (month == 12) {
                month = 1;
                year++;
            } else month++;
        } else day++;
    }

    public void previousDay() {
        if (day == 1) {
            if (month == 1) {
                month = 12;
                year--;
            } else month--;
            day = YearMonth.of(year, month).lengthOfMonth();
        } else day--;
    }

    public void nextMonth() {
        if (month == 12) {
            month = 1;
            year++;
        } else month++;
    }

    public void previousMonth() {
        if (month == 1) {
            month = 12;
            year--;
        } else month--;
    }

    public void nextYear() {
        year++;
    }

    public void previousYear() {
        year--;
    }

    private String monthHeader() {
        String headerText = String.format("%s %d", Month.of(month).toString(), year);
        String days = String.join(" ", Arrays.stream(DayOfWeek.values()).map(day -> day.name().substring(0, 2)).toArray(String[]::new));
        return " ".repeat(days.length() / 2 - headerText.length() / 2) + headerText + System.getProperty("line.separator") + days;
    }

    private String monthBody() {
        int startAt = weekDayIndex(year, month, 1);
        StringBuilder sb = new StringBuilder();
        sb.append("   ".repeat(startAt));

        for (int i = 0, dayOfMonth = 1; dayOfMonth <= monthLength; i++) {
            for (int j = ((i == 0) ? startAt : 0); j < 7 && (dayOfMonth <= monthLength); j++) {
                sb.append(String.format("%2d ", dayOfMonth));
                dayOfMonth++;
            }
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }
}
