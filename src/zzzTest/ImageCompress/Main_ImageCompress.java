package zzzTest.ImageCompress;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

public class Main_ImageCompress {

    private static final long defaultImageSize2 = 3 * 1024 * 1024;  //3M
    private static final long defaultImageSize = 300 * 1024;  //300kb
    private static final double baseDouble = 1.0;

    public static void main(String[] args) {
//        String filePath = "https://fuss10.elemecdn.com/b/3e/e2239b04033689d083ffe3f89875fjpeg.jpeg";
        String filePath = "/Users/nibnait/Desktop/1.jpg";
        String newfilePath = "/Users/nibnait/Desktop/new.jpg";
        String newfilePath2 = "/Users/nibnait/Desktop/new2.jpg";

        try {
            File imgFile = new File(filePath);
            BufferedImage imgBuffered = ImageIO.read(imgFile);
            int width = imgBuffered.getWidth(null);
            int height = imgBuffered.getHeight(null);

            long length = imgFile.length();
            if (length> defaultImageSize) {
                double scale = (baseDouble*defaultImageSize)/(baseDouble*length);
                double sqrtScale = Math.sqrt(scale);
                int newWidth = Double.valueOf(width * sqrtScale).intValue();
                int newHeight = Double.valueOf(height * sqrtScale).intValue();
                BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR );
                BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );
                image.getGraphics().drawImage(imgBuffered, 0, 0, newWidth, newHeight, null); // 绘制缩小后的图像
                image2.getGraphics().drawImage(imgBuffered, 0, 0, newWidth, newHeight, null); // 绘制缩小后的图像

                //存放的地址
                File destFile = new File(newfilePath);
                FileOutputStream out = new FileOutputStream(destFile);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(image);
                out.close();

                File destFile2 = new File(newfilePath2);
                FileOutputStream out2 = new FileOutputStream(destFile2);
                JPEGImageEncoder encoder2 = JPEGCodec.createJPEGEncoder(out2);
                encoder2.encode(image2);
                out2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
