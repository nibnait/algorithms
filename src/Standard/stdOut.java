package Standard;

/******************************************************************************
 *  Compilation:  javac stdOut.java
 *  Execution:    java stdOut
 *  Dependencies: none
 *
 *  Writes data of various types to standard output.
 *
 ******************************************************************************/

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 *  This class provides methods for printing strings and numbers to standard output.
 *  <p>
 *  <b>Getting started.</b>
 *  To use this class, you must have <tt>stdOut.class</tt> in your
 *  Java classpath. If you used our autoinstaller, you should be all set.
 *  Otherwise, download
 *  <a href = "http://introcs.cs.princeton.edu/java/stdlib/StdOut.java">stdOut.java</a>
 *  and put a copy in your working directory.
 *  <p>
 *  Here is an example program that uses <code>stdOut</code>:
 *  <pre>
 *   public class TestStdOut {
 *       public static void main(String[] args) {
 *           int a = 17;
 *           int b = 23;
 *           int sum = a + b;
 *           stdOut.println("Hello, World");
 *           stdOut.printf("%d + %d = %d\n", a, b, sum);
 *       }
 *   }
 *  </pre>
 *  <p>
 *  <b>Differences with System.out.</b>
 *  The behavior of <code>stdOut</code> is similar to that of {@link System#out},
 *  but there are a few subtle differences:
 *  <ul>
 *  <li> <code>stdOut</code> coerces the character-set encoding to UTF-8,
 *       which is a standard character encoding for Unicode.
 *  <li> <code>stdOut</code> <em>flushes</em> standard output after each call to
 *       <code>print()</code> so that text will appear immediately in the terminal.
 *  </ul>
 *  <p>
 *  <b>Reference.</b>
 *  For additional documentation,
 *  see <a href="http://introcs.cs.princeton.edu/15inout">Section 1.5</a> of
 *  <em>Introduction to Programming in Java: An Interdisciplinary Approach</em>
 *  by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class stdOut {

    // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with StdIn
    private static final Locale LOCALE = Locale.US;

    // send output here
    private static PrintWriter out;

    // this is called before invoking any methods
    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }

    // don't instantiate
    private stdOut() { }

   /**
     * Closes standard output.
     */
    public static void close() {
        out.close();
    }

   /**
     * Flushes standard output.
     */
    public static void print() {
        out.flush();
    }

   /**
     * Prints a formatted string to standard output, using the specified format
     * string and arguments, and then flushes standard output.
     *
     *
     * @param format the <a href = "http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args   the arguments accompanying the format string
     */
    public static void printf(String format, Object... args) {
        out.printf(LOCALE, format, args);
        out.flush();
    }

   /**
     * Prints a formatted string to standard output, using the locale and
     * the specified format string and arguments; then flushes standard output.
     *
     * @param locale the locale
     * @param format the <a href = "http://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args   the arguments accompanying the format string
     */
    public static void printf(Locale locale, String format, Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }
    //-------------输出char型数组 begin--------------------------------
    public static void print(char[] chars){
        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1) {
                out.println(chars[i]);
            }else {
                out.print(chars[i]+", ");
            }
        }
    }



    //-------------输出char型数组 end--------------------------------

    //-------------输出数组 begin--------------------------------

    public static void print(int[][] a){
        int n = a.length;
        int m = a[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void print(int[] a){
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                out.println(a[i]);
            }else {
                out.print(a[i]+", ");
            }
        }
    }

    //-------------输出数组 end----------------------------------

    //-------------打印链表 begin-------------------------------
    public static void printList(ListNode head){
        while (head != null){
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void printDoubleLinkedList(Node head){
        System.out.println("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();

    }

    //-------------打印链表 end-------------------------------


    //-------------二叉树的前中后需遍历 begin---------------------------------
    public static void preOrderPrint(Node head) {
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderPrint(head.left);
        preOrderPrint(head.right);
    }

    public static void inOrderPrint(Node head) {
        if (head == null){
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void posOrderPrint(Node head) {
        if (head == null){
            return;
        }
        posOrderPrint(head.left);
        posOrderPrint(head.right);
        System.out.print(head.value + " ");
    }
    //-------------二叉树的前中后需遍历 end-------------------------------


    //-------------打印二叉树 begin---------------------------------
    private static final int NODE_LENGTH = 17;      //二叉树中每个节点的长度

    public static void printBinaryTree(Node head) {

        System.out.println("Binary Tree：");
        printInOrder(head, 0, "*");
        System.out.println();
    }

    private static void printInOrder(Node head, int height, String to) {
        if (head == null){
            return;
        }
        printInOrder(head.left, height+1, "~");
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (NODE_LENGTH - lenM) / 2;
        int lenR = NODE_LENGTH - lenL - lenM;
        val = getSpace(height*NODE_LENGTH + lenL) + val + getSpace(lenR);
        System.out.println(val);
        printInOrder(head.right, height+1, "_");
    }

    private static String getSpace(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    //-------------打印二叉树 end---------------------------------


}
