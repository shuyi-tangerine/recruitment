package com.shuyi.recruitment.tencent.api.impl;

import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import com.shuyi.recruitment.tencent.api.TencentRecruitmentApi;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class TencentRecruitmentApiImpl implements TencentRecruitmentApi {
    @Override
    public List<PostDTO> postQuery(String url) {
        return List.of();
    }

    @Override
    public PostDTO postByPostId(String url) {
        return null;
    }
}