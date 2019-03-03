package A_ReadingNotes.Java8_in_Action.Part2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Chapter5_Main {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1、找出2011年的所有交易，并按交易额排序（从低到高）
//        System.out.println(test01(transactions));
        //2、交易员都在哪些不同的城市工作过
//        System.out.println(test02(transactions));
        //3、查找所有来自于剑桥的交易员，并按姓名排序
//        System.out.println(test03(transactions));
        //4、返回所有交易员的姓名字符串，按字母顺序排序
//        System.out.println(test04(transactions));
        //5、有没有交易员是在米兰工作的
//        System.out.println(test05(transactions));
        //6、打印生活在剑桥的交易员的所有交易额
//        System.out.println(test06(transactions));
        //7、所有交易中，最高的交易额是多少
//        System.out.println(test07(transactions));
        //8、找到交易额最小的交易
        System.out.println(test08(transactions));
    }

    private static Object test08(List<Transaction> transactions) {
//  蠢      return transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList()).get(0);
//  使用reduce
//      return transactions.stream().reduce((p1,p2) -> p1.getValue()>p2.getValue() ? p1 : p2);
        return transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
    }

    private static Object test07(List<Transaction> transactions) {
//  蠢      return transactions.stream().map(Transaction::getValue).sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0);
        return transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    private static Object test06(List<Transaction> transactions) {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("cambridge"))
                .forEach(transaction -> {
                    System.out.println(transaction.getTrader().getName() +":" + transaction.getValue());
                });
        return "以上";
    }

    private static Object test05(List<Transaction> transactions) {
//  low      return transactions.stream().map(transaction -> transaction.getTrader()).filter(trader -> trader.getCity().equalsIgnoreCase("Milan")).distinct().map(Trader::getName).collect(Collectors.toList());
        return transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equalsIgnoreCase("milan"));
    }

    private static Object test04(List<Transaction> transactions) {
//  low:      transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().forEach(item -> System.out.println(item));
//  String反复拼接效率低
//      return transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce((n1,n2) -> n1+n2);
        //高级！Collectors.joining()内部会用到StringBuilder
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
    }

    private static Object test03(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private static Object test02(List<Transaction> transactions) {
        return transactions.stream()
//                .map(Transaction::getTrader)
//                .map(Trader::getCity)
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
    }

    private static Object test01(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }
}
