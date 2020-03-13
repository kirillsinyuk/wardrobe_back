package com.commons.services.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomUtils {

    private RandomUtils(){}

    public static int getRandomMills(){
        int sleep = (int) (Math.random() * 10000);
        log.info("thread sleep for {} sec", sleep);
        return sleep;
    }

    public static void threadSleepRandom() {
        try {
            Thread.sleep(getRandomMills());
        } catch (InterruptedException e) {
            log.error("error while sleep:", e);
        }
    }
}
