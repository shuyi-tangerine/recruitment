package com.shuyi.recruitment.tencent.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
public class UpsertManagerTest {

    @Autowired
    private UpsertManager upsertManager;

    @Test
    public void upsertByStartTime() {
        long startUnixSeconds = Instant.now().getEpochSecond() - 30 * 24 * 60 * 60;
        startUnixSeconds = System.currentTimeMillis() / 1000  - 30 * 24 * 60 * 60;
        startUnixSeconds = ZonedDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()).toEpochSecond();
        upsertManager.upsertByStartTime(startUnixSeconds);
    }

    @Test
    public void upsertAmountPost() {
        int amount = 5000;
        upsertManager.upsertAmountPost(amount);
    }
}
