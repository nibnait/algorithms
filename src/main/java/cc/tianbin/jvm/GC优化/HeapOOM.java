package cc.tianbin.jvm.GC优化;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试GC
 *
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails -XX:+UseG1GC
 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/nibnait/Desktop/dump
 -verbose:gc
 -Xloggc:/Users/nibnait/Desktop/HeapOOM_heap_trace.log



 -Xms1024m -Xmx1024m -Xmn512m
 -XX:+UseG1GC
 -XX:MaxGCPauseMillis=20
 -XX:+UnlockExperimentalVMOptions
 -XX:InitiatingHeapOccupancyPercent=56
 -Xloggc:/Users/nibnait/Desktop/HeapOOM_gc.log
 -XX:+PrintGCTimeStamps
 -XX:+PrintGCDateStamps
 -XX:+PrintGCDetails
 -XX:+UseGCLogFileRotation
 -XX:NumberOfGCLogFiles=10
 -XX:GCLogFileSize=512K
 -XX:+HeapDumpOnOutOfMemoryError
 -XX:HeapDumpPath=/Users/nibnait/Desktop/HeapOOM_heapdump.hprof


 * JProfile
 *
 */
public class HeapOOM {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            map.put(i, i + "");
        }

    }

}