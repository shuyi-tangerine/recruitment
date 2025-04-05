package com.shuyi.recruitment.repository.dao;

import com.shuyi.recruitment.common.entity.TencentJobDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TencentJobDaoTest {

    @Autowired
    private TencentJobDAO tencentJobDAO;

    @Test
    void upsert() {
        TencentJobDO tencentJobDO = this.tencentJobDAO.upsert(TencentJobDO.builder()
                .postID("test_upsert_data04")
                .build());
        System.out.println(tencentJobDO);
    }
}
