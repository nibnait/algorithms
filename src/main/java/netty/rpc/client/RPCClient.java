package netty.rpc.client;

import com.alibaba.fastjson.JSON;
import io.netty.util.CharsetUtil;
import netty.rpc.client.request.RPCException;
import netty.rpc.client.request.RequestId;
import netty.rpc.client.request.ResponseRegistry;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by nibnait on 2019-08-20
 */
public class RPCClient {

    private String ip;
    private int port;
    private Socket sock;
    private DataInputStream input;
    private OutputStream output;

    public RPCClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() throws IOException {
        SocketAddress addr = new InetSocketAddress(ip, port);
        sock = new Socket();
        sock.connect(addr, 5000); // 5s超时
        input = new DataInputStream(sock.getInputStream());
        output = sock.getOutputStream();
    }

    public void close() {
        // 关闭链接
        try {
            sock.close();
            sock = null;
            input = null;
            output = null;
        } catch (IOException e) {
        }
    }
    public Object send(String type, Object payload) {
        // 普通rpc请求，正常获取响应
        try {
            return this.sendInternal(type, payload, false);
        } catch (IOException e) {
            throw new RPCException(e);
        }
    }
    public RPCClient rpc(String type, Class<?> clazz) {
        // rpc响应类型注册快捷入口
        ResponseRegistry.register(type, clazz);
        return this;
    }
    public void cast(String type, Object payload) {
        // 单向消息，服务器不得返回结果
        try {
            this.sendInternal(type, payload, true);
        } catch (IOException e) {
            throw new RPCException(e);
        }
    }
    private Object sendInternal(String type, Object payload, boolean cast) throws IOException {
        if (output == null) {
            connect();
        }
        String requestId = RequestId.next();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream buf = new DataOutputStream(bytes);
        writeStr(buf, requestId);
        writeStr(buf, type);
        writeStr(buf, JSON.toJSONString(payload));
        buf.flush();
        byte[] fullLoad = bytes.toByteArray();
        try {
            // 发送请求
            output.write(fullLoad);
        } catch (IOException e) {
            // 网络异常要重连
            close();
            connect();
            output.write(fullLoad);
        }
        if (!cast) {
            // RPC普通请求，要立即获取响应
            String reqId = readStr();
            // 校验请求ID是否匹配
            if (!requestId.equals(reqId)) {
                close();
                throw new RPCException("request id mismatch");
            }
            String typ = readStr();
            Class<?> clazz = ResponseRegistry.get(typ);
            // 响应类型必须提前注册
            if (clazz == null) {
                throw new RPCException("unrecognized rpc response type=" + typ);
            }
            // 反序列化json串
            String payld = readStr();
            Object res = JSON.parseObject(payld, clazz);
            return res;
        }
        return null;
    }
    private String readStr() throws IOException {
        int len = input.readInt();
        byte[] bytes = new byte[len];
        input.readFully(bytes);
        return new String(bytes, CharsetUtil.UTF_8);
    }
    private void writeStr(DataOutputStream out, String s) throws IOException {
        out.writeInt(s.length());
        out.write(s.getBytes(CharsetUtil.UTF_8));
    }
}