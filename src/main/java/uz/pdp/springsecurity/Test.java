package uz.pdp.springsecurity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
    }
}
