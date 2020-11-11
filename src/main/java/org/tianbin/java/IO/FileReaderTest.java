package org.tianbin.java.IO;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nibnait on 2020-02-23
 */
public class FileReaderTest extends TestCase {

    @Test
    public void testNioFile() {
        String filePath = "/Users/nibnait/github/algorithms/src/main/test/org/tianbin/java/IO/1.txt";
        Path path = Paths.get(filePath);

        Path absolutePath = path.toAbsolutePath();
        Path normalize = path.normalize();
        File file = path.toFile();

        try {

            System.out.println("一次性读取所有的字节流：");
            byte[] bytes = Files.readAllBytes(path);
            String string = new String(bytes);
            System.out.println(string);

            System.out.println("\n拿到InputStream，一次读取1024个字节：");
            InputStream inputStream = Files.newInputStream(path);
            string = readFromInputStream(inputStream);
            System.out.println(string);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFromInputStream(InputStream inputStream) throws IOException {
        byte[] byteBuffer = new byte[1024];

        StringBuilder sb = new StringBuilder();
        while (inputStream.read(byteBuffer) != -1) {
            sb.append(new String(byteBuffer));
        }

        return sb.toString();
    }

    @Test
    public void testIOFile() {
        File relativePathFile = new File("./src/main/test/org/tianbin");
        File absolutePathFile = new File("/Users/nibnait/github/algorithms/src/main/test/org/tianbin");

        File[] relativePathFiles = relativePathFile.listFiles();
        System.out.println("相对路径 " + JSON.toJSONString(relativePathFiles));
        System.out.println("绝对路径 " + JSON.toJSONString(absolutePathFile.listFiles()));

        StringBuilder catalog = new StringBuilder();
        backTrackPrintFileDir(relativePathFiles, 0, new StringBuilder(), catalog);

        System.out.println(catalog.toString());
    }

    private void backTrackPrintFileDir(File[] relativePathFiles, int i, StringBuilder tab, StringBuilder catalog) {

        while (i < relativePathFiles.length) {
            File currentFile = relativePathFiles[i++];

            if (currentFile.isFile()) {
                catalog.append(tab);
                catalog.append(currentFile.getName());
                catalog.append("\n");
            }

            if (currentFile.isDirectory()) {
                catalog.append(tab);
                catalog.append(currentFile.getName() + "/");
                catalog.append("\n");

                tab.append("\t");

                backTrackPrintFileDir(currentFile.listFiles(), 0, tab, catalog);

                tab.delete(tab.length()-1, tab.length());
            }
        }

    }
}
