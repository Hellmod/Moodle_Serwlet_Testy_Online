package SQLlight;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    public static void main(String[] args) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm");

        Date data = new Date();
        System.out.println(ft.format(data));
        data.setTime(data.getTime()+(10*60000));
        System.out.println(ft.format(data));



        /*
        String startDataString = result.getString("startData");
        dateStop = ft.parse(startDataString);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateStop);
        cal.add(Calendar.MINUTE, test.getIleMin());
        dateStop = cal.getTime();

         */
    }


}