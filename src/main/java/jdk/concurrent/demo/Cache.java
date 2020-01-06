package jdk.concurrent.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 基于ReentrantReadWriteLock 实现的缓存类
 *
 * @author nibnait
 * @version $Id: Cache.java, v 0.1 2019-08-31 下午8:19 nibnait Exp $$
 */
class Cache<K, V> {
    final Map<K, V> map = new HashMap<>();
    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 读锁
    final Lock readLock = readWriteLock.readLock();
    // 写锁
    final Lock writeLock = readWriteLock.writeLock();

    // 读缓存
    V get(K key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    // 写缓存
    V put(K key, V v) {
        writeLock.lock();
        try {
            return map.put(key, v);
        } finally {
            writeLock.unlock();
        }
    }
}