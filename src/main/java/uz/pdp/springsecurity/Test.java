package uz.pdp.springsecurity;

import java.io.FileNotFoundException;
import java.sql.Date;


public class Test {
    static String path = "src/main/resources/invoice.pdf";

    public static void main(String[] args) throws FileNotFoundException {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
    }

}
