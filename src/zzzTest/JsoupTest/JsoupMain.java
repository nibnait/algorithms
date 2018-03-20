package zzzTest.JsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupMain {

    public static void main(String[] args) {
        try {
            //解析Url获取Document对象
            Document document = Jsoup.connect("http://www.open-open.com/jsoup/").get();
            //获取网页源码文本内容
            System.out.println(document.toString());
            //获取指定class的内容指定tag的元素
            Elements liElements = document.getElementsByClass("content").get(0).getElementsByTag("li");
            for (int i = 0; i < liElements.size(); i++) {
                System.out.println(i + ". " + liElements.get(i).text());
            }
        } catch (IOException e) {
            System.out.println("解析出错！");
            e.printStackTrace();
        }

    }
}
