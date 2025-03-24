package com.shuyi.recruitment.tencent.api;

import com.shuyi.recruitment.common.dto.tencent.PostByPostIdRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryResponseDataDTO;
import com.shuyi.recruitment.common.dto.tencent.ResponseDTO;
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
        PostQueryRequestDTO requestDTO = new PostQueryRequestDTO();
        requestDTO.setTimestamp(System.currentTimeMillis());
        requestDTO.setCategoryId(40001001);
        requestDTO.setAttrId(1);
        requestDTO.setCityId(List.of(1, 5));
        requestDTO.setPageIndex(1);
        requestDTO.setPageSize(10);
        requestDTO.setLanguage("zh-cn");
        requestDTO.setArea("cn");
        ResponseDTO<PostQueryResponseDataDTO> responseDTO = tencentRecruitmentApi.postQuery(requestDTO);
        System.out.println(responseDTO);
    }

    @Test
    public void postByPostId() {
        PostByPostIdRequestDTO requestDTO = new PostByPostIdRequestDTO();
        requestDTO.setTimestamp(System.currentTimeMillis());
        requestDTO.setPostId("1861327447329370112");
        requestDTO.setLanguage("zh-cn");
        ResponseDTO<PostDTO> responseDTO = tencentRecruitmentApi.postByPostId(requestDTO);
        System.out.println(responseDTO);
    }
}
