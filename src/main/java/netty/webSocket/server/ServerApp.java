package netty.webSocket.server;

/**
 * @author nibnait
 * @version $Id: ClientApp.java, v 0.1 2019-08-19 下午8:33 nibnait Exp $$
 */
public class ServerApp {
    public static void main(String[] args) {
        System.out.println("Socket服务器启动！");
        SocketServer.start();
    }
}