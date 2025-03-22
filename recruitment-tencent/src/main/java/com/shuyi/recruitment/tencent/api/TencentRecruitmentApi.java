package com.shuyi.recruitment.tencent.api;

import com.shuyi.recruitment.common.dto.tencent.PostDTO;

import java.util.List;

public interface TencentRecruitmentApi {

    /*
     * https://careers.tencent.com/tencentcareer/api/post/Query
     */
    List<PostDTO> postQuery(String url);

    /*
     * https://careers.tencent.com/tencentcareer/api/post/ByPostId
     */
    PostDTO postByPostId(String url);
}
