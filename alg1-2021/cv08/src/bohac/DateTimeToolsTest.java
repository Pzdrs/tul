package bohac;

public class DateTimeToolsTest {
    public static void main(String[] args) {
        System.out.println("presupny rok - roky 2000, 2002, 2021");
        System.out.println(DateTimeTools.leapYear(2000));
        System.out.println(DateTimeTools.leapYear(2002));
        System.out.println(DateTimeTools.leapYear(2021));

        System.out.println("validace dat - 2.7.2003, 12.13.2021, 36.1.1900");
        System.out.println(DateTimeTools.dateValid("2.7.2003"));
        System.out.println(DateTimeTools.dateValid("12.13.2021"));
        System.out.println(DateTimeTools.dateValid("36.1.1900"));

        System.out.println("validace casu - 12:00:01, 13:00:01, 1:00:01");
        System.out.println(DateTimeTools.timeValid("12:00:01", true));
        System.out.println(DateTimeTools.timeValid("13:00:01", false));
        System.out.println(DateTimeTools.timeValid("1:00:01", true));

        System.out.println("dny v mesici - unor 2020, cerven 1985, duben 2002");
        System.out.println(DateTimeTools.daysInMonth(2,2020));
        System.out.println(DateTimeTools.daysInMonth(6,1985));
        System.out.println(DateTimeTools.daysInMonth(4,2002));

        System.out.println("den podle data - 19.11.2021, 19.11.2022, 19.11.2023,");
        System.out.println(DateTimeTools.dayOfWeek(2021,11,19));
        System.out.println(DateTimeTools.dayOfWeek(2022,11,19));
        System.out.println(DateTimeTools.dayOfWeek(2023,11,19));
    }
}
