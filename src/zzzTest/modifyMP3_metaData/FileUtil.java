package zzzTest.modifyMP3_metaData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    /**
     * 根据路径获取歌曲文件地址，可以是文件或者是文件夹
     *
     * @param sourceDir
     * @return
     */
    public static List<String> getFiles(String sourceDir) {
        List<String> mp3List = new ArrayList<String>();
        String[] files = null;

        File file = new File(sourceDir);
        if (!file.exists()) {
            System.out.println("文件或文件夹不存在");
            return mp3List;
        }

        if (file.isDirectory()) {
            files = file.list();
        } else if (file.isFile()) {
            files = new String[1];
            files[0] = sourceDir;
        }

        if (null == files || files.length == 0) {
            return mp3List;
        }
        return filterMp3Files(files);
    }

    /**
     * 筛选Mp3文件，将不是Mp3格式的文件都去除掉
     *
     * @param files
     */
    public static List<String> filterMp3Files(String[] files) {
        List<String> mp3List = new ArrayList<String>();
        String suffix = "";//后缀
        for (String str : files) {
            suffix = str.substring(str.lastIndexOf(".") + 1);
            if ("mp3".equalsIgnoreCase(suffix)) {
                mp3List.add(str);
            }
        }
        return mp3List;
    }
}
