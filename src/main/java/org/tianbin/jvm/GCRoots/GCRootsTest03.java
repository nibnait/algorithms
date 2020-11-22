package org.tianbin.jvm.GCRoots;

/**
 * 测试：常量引用对象作为GCRoots
 *
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 * -XX:+UseG1GC
 *
 * 注意：t修饰符如果只是final会被回收，static final不会被回收，所以static final 才是常量的正确写法
 * Created by nibnait on 2020/11/18
 */
public class GCRootsTest03 {

    private static int _10MB = 10 * 1024 * 1024;
    private static final GCRootsTest03 t = new GCRootsTest03(8 * _10MB);
    private byte[] memory;

    public GCRootsTest03(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        GCRootsTest03 t3 = new GCRootsTest03(4 * _10MB);
        t3 = null;
        System.gc();
    }

}

/*
CMS:
[GC (System.gc()) [PSYoungGen: 154337K->720K(458752K)] 154337K->82648K(983040K), 0.0403358 secs] [Times: user=0.50 sys=0.02, real=0.04 secs]
[Full GC (System.gc()) [PSYoungGen: 720K->0K(458752K)] [ParOldGen: 81928K->82431K(524288K)] 82648K->82431K(983040K), [Metaspace: 3104K->3104K(1056768K)], 0.0119135 secs] [Times: user=0.12 sys=0.00, real=0.01 secs]

G1:
[Full GC (System.gc())  122M->80M(1024M), 0.0101660 secs]
   [Eden: 3072.0K(512.0M)->0.0B(512.0M) Survivors: 0.0B->0.0B Heap: 122.0M(1024.0M)->80.5M(1024.0M)], [Metaspace: 3104K->3104K(1056768K)]
 [Times: user=0.00 sys=0.00, real=0.01 secs]

t3被置为null，Minor GC后t3之前引用的对象（40M）被完全回收；
t为常量，存放于方法区中，引用了对象（80M），在Minor GC后，被转移到老年代中，且在Full GC后，也不会被回收，继续保留在老年代中。

 */