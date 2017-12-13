package zzzTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListTest {

    public static void main(String[] args) {

    }

    private void testIsEmpty() {
        List<String> applyIds = null;
        System.out.println(applyIds.isEmpty());
    }

    private String[] getStringArray(List<Integer> applyIds) {
        if (applyIds!=null && applyIds.size()>0){
            String[] strings = new String[applyIds.size()];
            int i = 0;
            for (Integer integer : applyIds) {
                strings[i++] = integer.toString();
            }
            return strings;
        }
        return null;
    }

    private static void testList2Array() {
        List<Integer> applyIds = new ArrayList<>();
        applyIds.add(123);
        applyIds.add(234);
        String[] strings = new String[applyIds.size()];
        try {
//            applyIds.toArray(strings);

//            final int i =applyIds.size();
//            Stream<String> stringStream = applyIds.stream().map(item -> item.toString());
//            String[] strArray = stringStream.toArray(item -> new String[i]);

            String[] strArray = applyIds.stream().map(item -> item.toString()).toArray(item -> new String[applyIds.size()]);

            System.out.println(Arrays.asList(strArray));

            System.out.println("----------------------------------------------------------");

            Arrays.asList(applyIds);


        } catch (Exception e) {
            e.printStackTrace();
        }
        deal(strings);
    }
    private static void deal(String... str) {
        System.out.println(str.toString());
    }

    private static void testDistince() {
        List<String> fatherList = new ArrayList<>();
        fatherList.add("快餐便当");
        fatherList.add(null);

        System.out.println(fatherList.toString());
        List<String> unique = fatherList.stream().distinct().collect(Collectors.toList());
        System.out.println(unique);
    }

    static class SponsorTypeDto{
        private int sourceId;
        private int sourceCount;

        public SponsorTypeDto(int sourceId, int sourceCount) {
            this.sourceId = sourceId;
            this.sourceCount = sourceCount;
        }

        public int getSourceId() {
            return sourceId;
        }

        public void setSourceId(int sourceId) {
            this.sourceId = sourceId;
        }

        public int getSourceCount() {
            return sourceCount;
        }

        public void setSourceCount(int sourceCount) {
            this.sourceCount = sourceCount;
        }
    }
}
