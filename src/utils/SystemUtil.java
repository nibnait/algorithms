package utils;

import java.net.InetAddress;

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
}
