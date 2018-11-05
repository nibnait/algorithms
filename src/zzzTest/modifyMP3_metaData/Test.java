package zzzTest.modifyMP3_metaData;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;


/**
 * 获得MP3文件的信息
 *
 */
public class Test {

    public static void main(String[] args) {
        //TODO 演示
        File MP3FILE = new File("/Users/nibnait/Downloads/徐来.给孩子的博物学.201806/ghz0405你能读懂波浪的信息吗？.mp3");
        try {
            Test info = new Test(MP3FILE);
            info.setCharset("UTF-8");
            System.out.println(info.getSongName());
            System.out.println(info.getArtist());
            System.out.println(info.getAlbum());
            System.out.println(info.getYear());
            System.out.println(info.getComment());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private String charset = "utf-8";//解析MP3信息时用的字符编码

    private byte[] buf;//MP3的标签信息的byte数组

    /**
     * 实例化一个获得MP3文件的信息的类
     * @param mp3 MP3文件
     * @throws IOException 读取MP3出错或则MP3文件不存在
     */
    public Test(File mp3) throws IOException{

        buf = new byte[128];//初始化标签信息的byte数组

        RandomAccessFile raf = new RandomAccessFile(mp3, "r");//随机读写方式打开MP3文件
        raf.seek(raf.length() - 128);//移动到文件MP3末尾
        raf.read(buf);//读取标签信息

        raf.close();//关闭文件

        if(buf.length != 128){//数据是否合法
            throw new IOException("MP3标签信息数据长度不合法!");
        }


    }

    /**
     * 获得目前解析时用的字符编码
     * @return 目前解析时用的字符编码
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 设置解析时用的字符编码
     * @param charset 解析时用的字符编码
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSongName(){
        try {
            return new String(buf,3,30,charset).trim();
        } catch (UnsupportedEncodingException e) {
            return new String(buf,3,30).trim();
        }
    }

    public String getArtist(){
        try {
            return new String(buf,33,30,charset).trim();
        } catch (UnsupportedEncodingException e) {
            return new String(buf,33,30).trim();
        }
    }

    public String getAlbum(){
        try {
            return new String(buf,63,30,charset).trim();
        } catch (UnsupportedEncodingException e) {
            return new String(buf,63,30).trim();
        }
    }

    public String getYear(){
        try {
            return new String(buf,93,4,charset).trim();
        } catch (UnsupportedEncodingException e) {
            return new String(buf,93,4).trim();
        }
    }

    public String getComment(){
        try {
            return new String(buf,97,28,charset).trim();
        } catch (UnsupportedEncodingException e) {
            return new String(buf,97,28).trim();
        }
    }


}

