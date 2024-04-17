package datetime;

import java.time.*;

public class TimePlayground {
    public static void main(String[] args) {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDate ld1 = LocalDate.of(2020, 1, 1);
        LocalTime lt1 = LocalTime.of(12, 0);
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt1 = LocalDateTime.of(ld, lt);
        // above are human readable date and time

        Instant i = Instant.now();
        // this returns date and time in machine readable format (epoch time)
        System.out.println(i);
        System.out.println();

        // period is used to calculate difference between two dates
        // output is in years, months, days
        Period p = Period.between(ld1, ld);
        System.out.println(p);
        System.out.println();
        System.out.printf("%d years, %d months, %d days%n", p.getYears(), p.getMonths(), p.getDays());
        System.out.println();


        // duration is used to calculate difference between two times
        // output is in hours, minutes, seconds, nanoseconds
        Duration d = Duration.between(lt1, lt);
        System.out.println(d);
        System.out.println();
        System.out.printf("%d hours, %d minutes, %d seconds, %d nanoseconds%n", d.toHours(), d.toMinutes(), d.toSeconds(), d.toNanos());
        System.out.println(d.toHoursPart());
        System.out.println();


        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
        ZonedDateTime zdt1 = ZonedDateTime.of(ldt1, ZoneId.of("America/New_York"));
        System.out.println(zdt1);
        System.out.println();


        LocalDateTime xmas = LocalDateTime.of(2021, 12, 25, 12, 0);
        // California - GMT-8
        ZonedDateTime zxmas = ZonedDateTime.of(xmas, ZoneId.of("-8"));
        System.out.println(zxmas.withZoneSameInstant(ZoneId.of("GMT+0")));
    }
}
