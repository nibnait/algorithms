package cc.tianbin.jvm.GCRoots;

/**
 * 测试：成员变量引用对象是否可作为GCRoots
 *
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 * -XX:+UseG1GC
 *
 * Created by nibnait on 2020/11/18
 */
public class GCRootsTest04 {
    private static int _10MB = 10 * 1024 * 1024;
    private GCRootsTest04 t;
    private byte[] memory;

    public GCRootsTest04(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        GCRootsTest04 t4 = new GCRootsTest04(4 * _10MB);
        t4.t = new GCRootsTest04(8 * _10MB);
        t4 = null;
        System.gc();
    }

}

/*
CMS:
[GC (System.gc()) [PSYoungGen: 154337K->736K(458752K)] 154337K->744K(983040K), 0.0008671 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
[Full GC (System.gc()) [PSYoungGen: 736K->0K(458752K)] [ParOldGen: 8K->511K(524288K)] 744K->511K(983040K), [Metaspace: 3099K->3099K(1056768K)], 0.0037167 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]

G1:
[Full GC (System.gc())  122M->512K(1024M), 0.0099201 secs]
   [Eden: 3072.0K(512.0M)->0.0B(512.0M) Survivors: 0.0B->0.0B Heap: 122.0M(1024.0M)->512.3K(1024.0M)], [Metaspace: 3104K->3104K(1056768K)]
 [Times: user=0.01 sys=0.00, real=0.01 secs]

t4被置为null，Minor GC后t4之前引用的对象（40M）被完全回收；
t为成员变量，也叫实例变量，不同于类变量（静态变量），前面讲到类变量是存储在方法区中，而成员变量是存储在堆内存的对象中的，和对象共存亡，所以是不能作为GC Roots的，从日志中也可看出t在MinorGC后，跟随t4一起被完全回收。不再占用任何空间。

 */