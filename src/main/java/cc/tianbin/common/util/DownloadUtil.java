package cc.tianbin.common.util;

import com.google.common.collect.Lists;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by nibnait on 2022/01/09
 */
public class DownloadUtil {
    public static void main(String[] args) throws InterruptedException {

        downloadA("http://tb.nsfocus.co/1R2A8952.jpg", "/Users/nibnait/Downloads/qiniuyun/1R2A8952.jpg");
        downloadB("http://tb.nsfocus.co/1R2A8961.jpg", "1R2A8961.jpg", "/Users/nibnait/Downloads/qiniuyun");

        List<String> urlList = Lists.newArrayList("http://tb.nsfocus.co/1R2A8962.jpg");

        for (String url : urlList) {
            String fileName = decode(url.substring(url.lastIndexOf("/")));
            downloadB(url, fileName, "/Users/nibnait/Downloads/qiniuyun");

            System.out.println(fileName);
            Thread.sleep(1000);
        }

    }

    public static String decode(String url) {
        try {
            String prevURL = "";
            String decodeURL = url;
            while (!prevURL.equals(decodeURL)) {
                prevURL = decodeURL;
                decodeURL = URLDecoder.decode(decodeURL, "UTF-8");
            }
            return decodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while decoding" + e.getMessage();
        }
    }


    /**
     * 根据链接地址下载文件
     *
     * @param downloadUrl 文件链接地址
     * @param path        下载存放文件地址 + 文件名
     */
    private static void downloadA(String downloadUrl, String path) {
        URL url = null;
        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            url = new URL(downloadUrl);
            dataInputStream = new DataInputStream(url.openStream());
            fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param downloadUrl 文件链接地址
     * @param filename    保存文件名
     * @param filePath    保存文件路径
     */
    private static void downloadB(String downloadUrl, String filename, String filePath) {
        URL url = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            url = new URL(downloadUrl);
            // 打开连接
            URLConnection con = url.openConnection();
            // 请求超时:5s
            con.setConnectTimeout(5 * 1000);
            inputStream = con.getInputStream();

            byte[] bytes = new byte[1024];
            // 读取到的数据长度
            int length;
            File savePath = new File(filePath);
            if (!savePath.exists()) {
                // 如果不存在当前文件夹，则创建该文件夹
                boolean mkdir = savePath.mkdirs();
                if (!mkdir) {
                    System.out.println("创建文件夹失败");
                    return;
                }
            }
            outputStream = new FileOutputStream(savePath.getPath() + "/" + filename);
            // 读取
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
