package zzzTest;

import utils.DateTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduleTest {
    public static void main(String[] args) throws ParseException {
        Date startDate = DateTimeUtil.toUtilDate(LocalDateTime.now());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {    //间隔时间都为6秒，因此，下一次的执行时间点=上一次程序执行完成的时间点+间隔时间
//        timer.scheduleAtFixedRate(new TimerTask() {    //间隔时间都为5秒，因此，下一次的执行时间点=上一次程序开始执行的时间点+间隔时间
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("execute task!"+ this.scheduledExecutionTime());
            }
        },startDate, 5 * 1000);
    }
}
