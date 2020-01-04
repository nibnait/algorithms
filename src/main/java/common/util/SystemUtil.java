package common.util;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class SystemUtil {
    private static final String DEFAULT_IP = "127.0.0.1";

    public static String getLocalIpAddress() {
        String ip = DEFAULT_IP;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = new String(inetAddress.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }
}
