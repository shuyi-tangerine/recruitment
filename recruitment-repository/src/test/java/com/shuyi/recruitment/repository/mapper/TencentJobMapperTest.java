package com.shuyi.recruitment.repository.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TencentJobMapperTest {

    @Autowired
    private TencentJobMapper tencentJobMapper;

    @Test
    void insertOne() {
        tencentJobMapper.insertOne(null);
    }
}
