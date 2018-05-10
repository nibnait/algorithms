package zzzTest.ImageCompress;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 图片压缩处理 
 */
public class ZipImgUtil {
    private final int IMG_WIDTH = 670;
    private final int IMG_HEIGHT = 560;

    private Image img;
    private int width;
    private int height;
    private static String urlImg2; //定义全局变量方便传地址
    /**
     * 添加有参和无参构造函数 
     */
    public ZipImgUtil(){}
    public ZipImgUtil(String imgPath) throws IOException {
        File file = new File(imgPath);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
    }
    /**
     * 确定是按照宽度还是高度进行压缩 
     * @param h int 最大高度 
     * @param w int 最大宽度 
     */
    public void resizeFix(int h, int w) throws IOException {
        if ( height/ width > h / w) {
            resizeByWidth(h);
        } else {
            resizeByHeight(w);
        }
    }
    /**
     * 以高度为基准，等比例放缩图片 
     * @param h int 新高度 
     */
    public void resizeByWidth(int h) throws IOException {
        int w = (int) ( width* h / height);
        resize(h, w);
    }
    /**
     * 以宽度为基准，等比例缩放图片 
     * @param w int 新宽度
     */
    public void resizeByHeight(int w) throws IOException {
        int h = (int) ( height* w /width );
        resize(h, w);
    }
    /**
     * 进行强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */
    public void resize(int h, int w) throws IOException {
        //参数是TYPE_3BYTE_BGR时，图片最清晰！
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_3BYTE_BGR );
        image.getGraphics().drawImage(img, 0, 0, w,h, null); // 绘制缩小后的图像  
        //存放的地址
        File destFile = new File(urlImg2);
        FileOutputStream out = new FileOutputStream(destFile); // 输出文件流  
        // 可以正常实现bmp、png、gif、jpg等图片压缩  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(image);    //编码  
        out.close();              //关闭流
    }

    /**
     * 压缩图片文件的方法
     * @param imgPath
     * @throws Exception
     */
    public void comparessImg(String imgPath) throws Exception {
        //实例对象
        StringBuffer img = new StringBuffer(imgPath);
        urlImg2 = img.toString();
        //有参构造方法
        ZipImgUtil imgCom = new ZipImgUtil(img.toString());
        //压缩
        imgCom.resizeFix(IMG_HEIGHT, IMG_WIDTH);
    }
}  