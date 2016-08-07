package aaaTest;


import Standard.Std;
import Standard.StdOut;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        int a[] = new int[4];
        a[1] = 1;
        a[2] = 2;
        StdOut.print(a);

        Std.swap(a,1,2);
        StdOut.print(a);

    }
}
