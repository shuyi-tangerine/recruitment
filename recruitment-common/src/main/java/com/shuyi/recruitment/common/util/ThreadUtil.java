package com.shuyi.recruitment.common.util;

public class ThreadUtil {

    public static void sleepSeconds(long seconds) {
        try {
            System.out.println("sleeping " + seconds + " seconds...");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
