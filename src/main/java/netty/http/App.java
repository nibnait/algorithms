package netty.http;

/**
 * @author nibnait
 * @version $Id: AppClient.java, v 0.1 2019-08-19 下午8:33 nibnait Exp $$
 */
public class App {
    public static void main(String[] args) {
        System.out.println("HTTP服务器启动！");
        HttpServer.start();
    }
}