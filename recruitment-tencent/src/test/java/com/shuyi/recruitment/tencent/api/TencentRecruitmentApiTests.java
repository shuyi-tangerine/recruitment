package com.shuyi.recruitment.tencent.api;

import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TencentRecruitmentApiTests {

    @Autowired
    private TencentRecruitmentApi tencentRecruitmentApi;

    @Test
    public void postQuery() {
        List<PostDTO> postDTOs = tencentRecruitmentApi.postQuery("");
        System.out.println(postDTOs);
    }
}
