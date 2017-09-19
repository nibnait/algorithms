package zzzTest;


import org.apache.commons.lang.text.StrBuilder;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        StrBuilder sb = new StrBuilder("iii");

        //创建一个新文件
        String filePath = "/Users/nibnait/Desktop/1.txt";
        Files.createFile(Paths.get(filePath));

        //写操作
        Files.write(Paths.get(filePath),sb.toString().getBytes());
    }
}

