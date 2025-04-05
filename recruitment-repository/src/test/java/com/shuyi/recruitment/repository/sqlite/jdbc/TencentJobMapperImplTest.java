package com.shuyi.recruitment.repository.sqlite.jdbc;

import com.shuyi.recruitment.repository.mapper.TencentJobMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TencentJobMapperImplTest {

    @Autowired
    private TencentJobMapper tencentJobMapper;

    @Test
    void insertOne() {
        tencentJobMapper.insertOne(null);
    }
}
