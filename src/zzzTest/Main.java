package zzzTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
}
