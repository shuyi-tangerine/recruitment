package com.shuyi.recruitment.tencent.api;

import com.shuyi.recruitment.common.dto.tencent.PostByPostIdRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryResponseDataDTO;
import com.shuyi.recruitment.common.dto.tencent.ResponseDTO;

import java.util.List;

public interface TencentRecruitmentApi {

    /*
     * https://careers.tencent.com/tencentcareer/api/post/Query
     * 允许原始的请求和最原始的返回
     */
    ResponseDTO<PostQueryResponseDataDTO> postQuery(String url);

    /*
     * 只返回有用的信息
     */
    ResponseDTO<PostQueryResponseDataDTO> postQuery(PostQueryRequestDTO requestDTO);

    /*
     * https://careers.tencent.com/tencentcareer/api/post/ByPostId
     * 允许原始的请求和最原始的返回
     */
    ResponseDTO<PostDTO> postByPostId(String url);

    /*
     * 只返回有用的信息
     */
    ResponseDTO<PostDTO> postByPostId(PostByPostIdRequestDTO requestDTO);
}
