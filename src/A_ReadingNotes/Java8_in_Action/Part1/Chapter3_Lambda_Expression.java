package A_ReadingNotes.Java8_in_Action.Part1;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Chapter3_Lambda_Expression {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Apple {
        int weight;
        String collor;

        List<Apple> init() {
            return Lists.newArrayList(new Apple(1, "红"),
                    new Apple(2, "黄"),
                    new Apple(3, "绿"),
                    new Apple(4, "红"));
        }
    }

    public static void main(String[] args){
//        try {
//            test01();     //声明函数式接口，传递Lambda表达式
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        test02();       //java.util.function.Predicate.test()方法练习
//        test03();       //java.util.function.Consumer.accept()方法练习
//        test04();       //java.util.function.Function.apply()方法练习
        test05();
    }

    private static void test05() {


    }

//********************* test04 java.util.function.Function.apply()方法练习**************************************
//******************************************    接收泛型T，返回一个泛型R。（将输入对象的信息，映射到另一个对象R上，返回）

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t: list) {
            result.add(f.apply(t));
        }
        return result;
    }

    private static void test04() {
        List<Integer> mapResult1 = map(Lists.newArrayList(new Apple(1, "1")),
                (Apple apple) -> apple.weight);
        System.out.println(mapResult1);
    }

//********************* test03 java.util.function.Consumer.accept()方法练习**************************************
//******************************************    接收泛型T，返回void，可以对T对象进行某些操作

    public static <T> void forEach(List<T> inventoryList, Consumer<T> c) {
        for (T t : inventoryList) {
            c.accept(t);
        }
    }

    private static void test03() {
        List<Apple> inventoryList = new Apple().init();
        forEach(inventoryList,
                (Apple apple) -> apple.weight = apple.weight+1);
        System.out.println(inventoryList);

        forEach(inventoryList,
                (Apple apple) -> System.out.println(apple));
    }

//********************* test02 java.util.function.Predicate.test()方法练习**************************************
//******************************************    接收泛型T，返回boolean

    public static <T> List<T> filter(List<T> inventoryList, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t: inventoryList) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    private static void test02() {
        Apple inventoryApple = new Apple();
        List<Apple> redApple = filter(inventoryApple.init(),
                                        (Apple apple) -> apple.collor.equals("红"));
        System.out.println(redApple.toString());
    }

//********************* test01 声明函数式接口，传递Lambda表达式**************************************

    /**
     * 声明函数式接口
     */
//    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    /**
     * 环绕执行模式，函数主题负责资源的打开和关闭等统一处理
     * @param p 一个接口，内部可以执行这个接口的所有方法。灵活性MAX
     *          如果接口看成是 函数式接口，灵活性MAX_double：可以直接使用与process方法相同的入参的Lambda表达式，
     * @return
     * @throws IOException
     */
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br =  new BufferedReader(new FileReader("/Users/nibnait/Desktop/1.txt"))) {
            return p.process(br);
        }   //带资源的try语句，不需要显示关闭资源！！
    }

    private static void test01() throws IOException {
        //传递Lambda表达式，处理文件
        //1. 返回前两行文字
        String first_two_line = processFile((BufferedReader br) -> br.readLine() + "\n" + br.readLine());
//        System.out.println(first_two_line);

        //2. 返回使用最频繁的字符
        String frequently_char = processFile((BufferedReader br) -> statFrequentlyChar(br));
        System.out.println(frequently_char);
    }

    private static String statFrequentlyChar(BufferedReader br) {
        return "1";
    }
}
