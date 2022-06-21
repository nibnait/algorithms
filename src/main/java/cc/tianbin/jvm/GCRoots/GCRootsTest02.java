package cc.tianbin.jvm.GCRoots;

/**
 * 测试：方法区中的静态变量引用的对象作为GCRoots
 *
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 * -XX:+UseG1GC
 *
 * 扩展：方法区存与堆一样，是各个线程共享的内存区域，用于存放已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
 * Created by nibnait on 2020/11/18
 */
public class GCRootsTest02 {

    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;

    private static GCRootsTest02 t;

    public GCRootsTest02(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        GCRootsTest02 t2 = new GCRootsTest02(4 * _10MB);
        t2.t = new GCRootsTest02(8 * _10MB);
        t2 = null;
        System.gc();
    }
}

/*
CMS:
[GC (System.gc()) [PSYoungGen: 154337K->720K(458752K)] 154337K->82648K(983040K), 0.0427416 secs] [Times: user=0.52 sys=0.02, real=0.05 secs]
[Full GC (System.gc()) [PSYoungGen: 720K->0K(458752K)] [ParOldGen: 81928K->82431K(524288K)] 82648K->82431K(983040K), [Metaspace: 3104K->3104K(1056768K)], 0.0116124 secs] [Times: user=0.13 sys=0.00, real=0.01 secs]

G1:
[Full GC (System.gc())  122M->80M(1024M), 0.0100156 secs]
   [Eden: 3072.0K(512.0M)->0.0B(512.0M) Survivors: 0.0B->0.0B Heap: 122.0M(1024.0M)->80.5M(1024.0M)], [Metaspace: 3104K->3104K(1056768K)]
 [Times: user=0.00 sys=0.01, real=0.01 secs]

t2被置为null，Minor GC后t2之前引用的对象（40M）被完全回收；
t为静态变量，存放于方法区中，引用了对象（80M），在Minor GC后，被转移到老年代中，且在Full GC后，也不会被回收，继续保留在老年代中。

 */
