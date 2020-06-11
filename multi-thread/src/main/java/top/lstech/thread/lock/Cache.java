package top.lstech.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: 义云
 * @Date: 2020/6/11 8:06 PM
 * @Version 1.0
 */
public class Cache {

    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    // 读锁， 获取一个key对应的value
    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    // 写锁，设置key对应的value， 并返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
            ;
        }
    }

    // 清空所有的内容, 读锁
    public static void main(String[] args) {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
