package zzzTest;

import org.apache.commons.collections4.CollectionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MapTest {
    public static void main(String[] args) throws Exception {
        Solution();
    }

    public static void Solution() throws Exception {
        String deskPath = "/Users/nibnait/Desktop/";
        Map<Integer, String> textMap = new HashMap<>();//行号、文字
        Map<Integer, ArrayList<Integer>> lineNumberMap = new TreeMap<>();//数字、行号
        ArrayList<Integer> sortedLineNumberList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        StringBuilder text = new StringBuilder();
        String tempString = null;
        int lineNo = 1;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            bufferedReader = new BufferedReader(new FileReader(deskPath+"in.txt"));
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = bufferedReader.readLine()) != null) {
                System.out.println("lineNo " + lineNo + ": " + tempString);
                textMap.put(lineNo, tempString);
                StringBuilder sb = new StringBuilder();
                char[] chars = tempString.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (Character.isDigit(chars[i])) {
                        sb.append(chars[i]);
                    }
                }
                int number = Integer.valueOf(sb.toString());
                ArrayList<Integer> numberList = lineNumberMap.get(number);
                if (CollectionUtils.isNotEmpty(numberList)) {
                    numberList.add(lineNo);
                    lineNumberMap.put(number, numberList);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(lineNo);
                    lineNumberMap.put(number, list);
                }

                lineNo++;
            }
            TreeMap<Integer, ArrayList<Integer>> treeMap = new TreeMap<>(lineNumberMap);
            for (Map.Entry<Integer, ArrayList<Integer>> entry : treeMap.entrySet()) {
//                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                ArrayList<Integer> arrayList = entry.getValue();
                for (Integer j : arrayList) {
                    text.append(textMap.get(j) + "\n");
                }
            }
            bufferedReader.close();

            //写文件
            Path filePath = Paths.get(deskPath+"out.txt");
//            FileOutputStream fileOutputStream = new FileOutputStream(text.toString());
//            Files.createFile(filePath);
            Files.write(filePath, text.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                }
            }
        }

    }
}
