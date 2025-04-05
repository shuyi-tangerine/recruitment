package com.shuyi.recruitment.common.util;

import java.util.Objects;
import java.util.Random;

public class ThreadUtil {

    public static void sleepSeconds(long seconds, String title) {
        sleepMillis(seconds * 1000, title);
    }

    public static void sleepRandomMillis(int startMillis, int endMillis, String title) {
        Random r = new Random();
        int millis = r.nextInt(startMillis, endMillis);
        sleepMillis(millis, title);
    }

    public static void sleepMillis(long millis, String title) {
        try {
            if (Objects.isNull(title) || title.isBlank()) {
                title = Thread.currentThread().getName();
            }
            System.out.println(title + " sleeping " + millis + "ms...");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
