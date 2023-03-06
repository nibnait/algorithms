package cc.tianbin.demo.jvm.GCRoots;

/**
 * 测试：虚拟机栈（栈帧中的局部变量）中引用的对象作为GCRoots
 *
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 * -XX:+UseG1GC
 *
 * 扩展：虚拟机栈中存放了编译器可知的八种基本数据类型,对象引用,returnAddress类型（指向了一条字节码指令的地址）
 * Created by nibnait on 2020/11/18
 */
public class GCRootsTest01 {

    private final int _10MB = 10 * 1024 * 1024;
    private final byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) {
        method01();
        System.out.println("返回main方法");
        System.gc();
        System.out.println("第二次GC完成");
    }

    public static void method01() {
        GCRootsTest01 t = new GCRootsTest01();
        System.gc();
        System.out.println("第一次GC完成");
    }

}

    /*
[GC (System.gc()) [PSYoungGen: 113377K->800K(458752K)] 113377K->82728K(983040K), 0.0573694 secs] [Times: user=0.69 sys=0.03, real=0.06 secs]
[Full GC (System.gc()) [PSYoungGen: 800K->0K(458752K)] [ParOldGen: 81928K->82432K(524288K)] 82728K->82432K(983040K), [Metaspace: 3104K->3104K(1056768K)], 0.0224396 secs] [Times: user=0.25 sys=0.01, real=0.02 secs]
第一次GC完成
返回main方法
[GC (System.gc()) [PSYoungGen: 7864K->128K(458752K)] 90296K->82560K(983040K), 0.0008938 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
[Full GC (System.gc()) [PSYoungGen: 128K->0K(458752K)] [ParOldGen: 82432K->471K(524288K)] 82560K->471K(983040K), [Metaspace: 3105K->3105K(1056768K)], 0.0053761 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
第二次GC完成


[Full GC (System.gc())  169M->161M(1024M), 0.0153233 secs]
   [Eden: 10.0M(512.0M)->0.0B(512.0M) Survivors: 0.0B->0.0B Heap: 169.5M(1024.0M)->161.2M(1024.0M)], [Metaspace: 5015K->5015K(1056768K)]
 [Times: user=0.01 sys=0.00, real=0.02 secs]
第一次GC完成
返回main方法
[Full GC (System.gc())  161M->81M(1024M), 0.0059987 secs]
   [Eden: 1024.0K(512.0M)->0.0B(512.0M) Survivors: 0.0B->0.0B Heap: 161.7M(1024.0M)->81.2M(1024.0M)], [Metaspace: 5015K->5015K(1056768K)]
 [Times: user=0.01 sys=0.00, real=0.00 secs]
第二次GC完成


第一次GC：
t为局部变量，引用了new出的对象（80M），作为GC Roots，在Minor GC后被转移到老年代中，且Full GC也不会回收该对象，仍保留在老年代中。

第二次GC：
method01方法执行完后，局部变量t跟随方法消失，不再有引用类型指向该对象，该对象在Full GC后，被完全回收，老年代腾出该对象之前所占的空间。

     */
