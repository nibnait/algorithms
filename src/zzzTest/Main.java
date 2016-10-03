package zzzTest;

import java.util.*;

public class Main{
    public static void main(String[] args){
        List<Integer> result = new LinkedList<>();
        result.add(null);
        result.add(1);
        int value = result.remove(result.size()-2);
        System.out.println(value);
    }
}
