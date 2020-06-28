package SQLlight;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    public static void main(String[] args) {

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd'T'hh:mm");
        String input = "2020-06-28T17:00";

        try {
            Date t = ft.parse(input);
            System.out.println(t);
        } catch (Exception e) {
            System.out.println("Unparseable using " + ft);
        }
/*
        Date dNow = new Date( );
        SimpleDateFormat ft =  new SimpleDateFormat ("yyyy-MM-dd'T'hh:mm");

        System.out.println(ft.format(dNow));
*/
    }
}