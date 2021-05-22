package org.cf.forgot.jdk.collections;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/14
 * @since todo
 */
public class ConcurrentHashMapDemo {

//    private static Random random = new Random(System.currentTimeMillis());
    private static AtomicInteger requestId = new AtomicInteger(0);
    private String key;

    public static void main(String[] args) {
        doTest();
    }
    private static long counter = 0;

    private static void doTest() {
        ConcurrentHashMap<String, String> map = createMap();
        while (map.size() > 0) {
            checkMap(map);
            addMsg(map, 10);
            sleepEnough(300);
        }
    }

    private static void checkMap(ConcurrentHashMap<String, String> map) {
        Set<String> keySet = map.keySet();
        while (keySet.size() > 0) {
            for (String k : keySet) {
                String value = map.get(k);
//            System.out.printf("key: %s, value: %s\n", k, value);
                if (counter++ != 20) {
                    map.remove(k);
                }
                if (counter == 10) {
                    map.put(1 + "", "aaaaaaaaaaaaaaaaaaaaaaaaaa");
                    map.put(2 + "", "aaaaaaaaaaaaaaaaaaaaaaaaaa");
                    map.put(3 + "", "aaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
                map.put(randomKey(), "random");
            }//end for
        }//end while
        System.out.println("-----------" + map.size());
    }

    private static String randomKey() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private static ConcurrentHashMap<String, String> createMap() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put(randomKey(), "aaaa");
        map.put(randomKey(), "aaaa");
        map.put(randomKey(), "aaaa");
        return map;
    }

    private static void sleepEnough(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addMsg(ConcurrentHashMap<String, String> map, int counter) {
        for (int i = 0; i < counter; i++) {
            map.put(randomKey(), "bbbbb");
        }
    }
}
