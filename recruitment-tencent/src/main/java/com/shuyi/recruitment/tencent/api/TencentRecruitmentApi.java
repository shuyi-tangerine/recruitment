package com.shuyi.recruitment.tencent.api;

import com.shuyi.recruitment.common.dto.tencent.PostByPostIdRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryResponseDataDTO;
import com.shuyi.recruitment.common.dto.tencent.ResponseDTO;

public interface TencentRecruitmentApi {

    /*
     * https://careers.tencent.com/tencentcareer/api/post/Query
     * 允许原始的请求和最原始的返回
     */
    ResponseDTO<PostQueryResponseDataDTO> postQuery(String url);
    ResponseDTO<PostQueryResponseDataDTO> postQuery(PostQueryRequestDTO requestDTO);
    PostQueryResponseDataDTO queryPostListPage(PostQueryRequestDTO requestDTO);

    /*
     * https://careers.tencent.com/tencentcareer/api/post/ByPostId
     * 查询详情
     */
    ResponseDTO<PostDTO> postByPostId(String url);
    ResponseDTO<PostDTO> postByPostId(PostByPostIdRequestDTO requestDTO);
    PostDTO queryPostByPostId(String postId);
}
