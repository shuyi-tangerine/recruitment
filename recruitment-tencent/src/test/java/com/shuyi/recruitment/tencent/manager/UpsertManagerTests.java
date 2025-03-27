package com.shuyi.recruitment.tencent.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
public class UpsertManagerTests {

    @Autowired
    private UpsertManager upsertTask;

    @Test
    public void upsertByStartTime() {
        long startUnixSeconds = Instant.now().getEpochSecond() - 30 * 24 * 60 * 60;
        startUnixSeconds = System.currentTimeMillis() / 1000  - 30 * 24 * 60 * 60;
        startUnixSeconds = ZonedDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()).toEpochSecond();
        upsertTask.upsertByStartTime(startUnixSeconds);
    }

    @Test
    public void upsertAmountPost() {
        int amount = 20;
        upsertTask.upsertAmountPost(amount);
    }
}
