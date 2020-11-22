package org.tianbin.jvm.引用;

import com.caucho.hessian.io.SerializerFactory;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

/**
 * Created by nibnait on 2020/11/12
 */
public class Reference {

    /**
     * 强引用
     */
    @Test
    public void testStrongReference() {

    }

    /**
     * 软引用：在系统要发生OOM之前 回收。仍没有足够的内存，再报OOM
     */
    @Test
    public void testSoftReference() {

        SerializerFactory serializerFactory;


    }

    /**
     * 弱引用：只会生存到下一次垃圾回收之前。无论内存是否足够 都会回收弱引用关联的对象
     */
    @Test
    public void testWeakReference() {
        // ThreadLocal.ThreadLocalMap
        ThreadLocal threadLocal;


        SerializerFactory serializerFactory;

    }

    /**
     * 设置虚引用：目的是能在这个对象被收集器回收时 能够收到一个系统通知。
     */

    public static boolean isRun = true;

    @Test
    public void testPhantomReference() throws InterruptedException {

        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        final ReferenceQueue referenceQueue = new ReferenceQueue<String>();
        new Thread() {
            public void run() {
                while (isRun) {
                    Object o = referenceQueue.poll();
                    if (o != null) {
                        try {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(o);
                            System.out.println("gc will collect:"
                                    + result.getClass() + "@"
                                    + result.hashCode());
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc,
                referenceQueue);
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
        isRun = false;

    }

}
