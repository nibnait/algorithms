package zzzTest.modifyMP3_metaData;

import org.apache.commons.lang3.StringUtils;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static String sourceFolder = "/Users/nibnait/Downloads/徐来.给孩子的博物学.201806";
    private static String NULL = StringUtils.EMPTY;
    private static byte defaultGenre = -1;
    private static String defaultGenreStr = "-1";

    public static void main(String[] args) {
        List<String> mp3StrList = FileUtil.getFiles(sourceFolder);
        if (null == mp3StrList || mp3StrList.size() == 0) {
            return;
        }
        for (String fileName : mp3StrList) {
            renameAlbumTitle(fileName);
        }
    }

    private static void renameAlbumTitle(String mp3FileStr) {
        String fileName = mp3FileStr.substring(0, mp3FileStr.lastIndexOf("."));
        String folderName = sourceFolder.substring(sourceFolder.lastIndexOf("/")+1);
        try {
            String filePath = sourceFolder + "/" + mp3FileStr;
            MP3File file = new MP3File(filePath);
            AbstractID3v2 id3v2 = file.getID3v2Tag();
            ID3v1 id3v1 = file.getID3v1Tag();
            if (id3v2 != null) {
                soutBeforeID3v2(id3v2);
                id3v2.setSongTitle(fileName);       //作曲家
                id3v2.setLeadArtist(NULL);          //艺术家相当于ID3v1的Artist
                id3v2.setAlbumTitle(folderName);    //专辑名
                id3v2.setYearReleased(NULL);        //灌录年份
                id3v2.setSongComment(NULL);         //注释
                id3v2.setSongGenre(NULL);           //流派（风格）
                id3v2.setTrackNumberOnAlbum(NULL);  //音轨号
                id3v2.setSongLyric(NULL);           //歌词
                id3v2.setAuthorComposer(NULL);      //作曲者
                file.setID3v2Tag(id3v2);
                soutAfterD3v2(id3v2);
            }
            if (id3v1 != null) {
                soutBeforeID3v1(id3v1);
                id3v1.setArtist(NULL);              //歌手名
                id3v1.setLeadArtist(NULL);
                id3v1.setAlbum(folderName);         //专辑名
                id3v1.setAlbumTitle(folderName);
                id3v1.setComment(NULL);             //注释
                id3v1.setSongComment(NULL);
                id3v1.setGenre(defaultGenre);       //歌曲风格
                id3v1.setSongGenre(defaultGenreStr);
                id3v1.setTitle(fileName);           //歌曲名
                id3v1.setSongTitle(fileName);
                id3v1.setYear(NULL);                //日期信息
                id3v1.setYearReleased(NULL);
                file.setID3v1Tag(id3v1);
                soutAfterD3v1(id3v1);
            }


            Path sourcePath = Paths.get(filePath);
            Path newPath = Paths.get(sourceFolder + "/" + "1.mp3");
            if (Files.exists(newPath)) {
                Files.delete(newPath);
                Files.createFile(newPath);
            } else {
                Files.createFile(newPath);
            }
            //写操作
            Files.write(newPath, getAllBytes(file));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 将File转成字节数组
     * @param file
     * @return
     */
    private static byte[] getAllBytes(MP3File file) throws IOException {
        byte[] bytesArray = new byte[10000000];
//        FileInputStream fis = new FileInputStream(file);
//        fis.read(bytesArray);
        return bytesArray;
    }

    private static void soutAfterD3v1(ID3v1 id3v1) {
        System.out.println("AfterD3v1#######################");
        System.out.println("getArtist: "+id3v1.getArtist());
        System.out.println("getLeadArtist: "+id3v1.getLeadArtist());
        System.out.println("getAlbum: "+id3v1.getAlbum());
        System.out.println("getAlbumTitle: "+id3v1.getAlbumTitle());
        System.out.println("getComment: "+id3v1.getComment());
        System.out.println("getSongComment: "+id3v1.getSongComment());
        System.out.println("getGenre: "+id3v1.getGenre());
        System.out.println("getSongGenre: "+id3v1.getSongGenre());
        System.out.println("getTitle: "+id3v1.getTitle());
        System.out.println("getSongTitle: "+id3v1.getSongTitle());
        System.out.println("getYear: "+id3v1.getYear());
        System.out.println("getYearReleased: "+id3v1.getYearReleased());
        System.out.println();
        System.out.println();
    }

    private static void soutBeforeID3v1(ID3v1 id3v1) {
        System.out.println("BeforeID3v1#######################");
        System.out.println("getArtist: "+id3v1.getArtist());
        System.out.println("getLeadArtist: "+id3v1.getLeadArtist());
        System.out.println("getAlbum: "+id3v1.getAlbum());
        System.out.println("getAlbumTitle: "+id3v1.getAlbumTitle());
        System.out.println("getComment: "+id3v1.getComment());
        System.out.println("getSongComment: "+id3v1.getSongComment());
        System.out.println("getGenre: "+id3v1.getGenre());
        System.out.println("getSongGenre: "+id3v1.getSongGenre());
        System.out.println("getTitle: "+id3v1.getTitle());
        System.out.println("getSongTitle: "+id3v1.getSongTitle());
        System.out.println("getYear: "+id3v1.getYear());
        System.out.println("getYearReleased: "+id3v1.getYearReleased());
        System.out.println();
        System.out.println();
    }

    private static void soutAfterD3v2(AbstractID3v2 id3v2) {
        System.out.println("AfterD3v2#######################");
        System.out.println("getSongTitle: "+id3v2.getSongTitle());
        System.out.println("getLeadArtist: "+id3v2.getLeadArtist());
        System.out.println("getAlbumTitle: "+id3v2.getAlbumTitle());
        System.out.println("getYearReleased: "+id3v2.getYearReleased());
        System.out.println("getSongComment: "+id3v2.getSongComment());
        System.out.println("getSongGenre: "+id3v2.getSongGenre());
        System.out.println("getTrackNumberOnAlbum: "+id3v2.getTrackNumberOnAlbum());
        System.out.println("getSongLyric: "+id3v2.getSongLyric());
        System.out.println("getAuthorComposer: "+id3v2.getAuthorComposer());
        System.out.println();
        System.out.println();
    }

    private static void soutBeforeID3v2(AbstractID3v2 id3v2) {
        System.out.println("BeforeID3v2#######################");
        System.out.println("getSongTitle: "+id3v2.getSongTitle());
        System.out.println("getLeadArtist: "+id3v2.getLeadArtist());
        System.out.println("getAlbumTitle: "+id3v2.getAlbumTitle());
        System.out.println("getYearReleased: "+id3v2.getYearReleased());
        System.out.println("getSongComment: "+id3v2.getSongComment());
        System.out.println("getSongGenre: "+id3v2.getSongGenre());
        System.out.println("getTrackNumberOnAlbum: "+id3v2.getTrackNumberOnAlbum());
        System.out.println("getSongLyric: "+id3v2.getSongLyric());
        System.out.println("getAuthorComposer: "+id3v2.getAuthorComposer());
        System.out.println();
        System.out.println();
    }

}
