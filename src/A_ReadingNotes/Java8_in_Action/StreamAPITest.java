package A_ReadingNotes.Java8_in_Action;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamAPITest {

    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article("title1", "author1", Lists.newArrayList("tag1")));
        articles.add(new Article("title2", "author2", Lists.newArrayList("tag1", "interview")));
        articles.add(new Article("title3", "author3", Lists.newArrayList("tag1", "interview", "tag3")));

        Optional<Article> firstJavaArticle = getFirstJavaArticle(articles);
        System.out.println(firstJavaArticle.toString());
        List<Article> allJavaArticles = getAllJavaArticles(articles);
        System.out.println(allJavaArticles.toString());

    }

    private static Optional<Article> getFirstJavaArticle(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("interview"))
                .findFirst();
    }

    private static  List<Article> getAllJavaArticles(List<Article> articles) {
        return articles.stream()
                .filter(article -> article.getTags().contains("interview"))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    private static class Article {
        private final String title;
        private final String author;
        private final List<String> tags;
    }

}
