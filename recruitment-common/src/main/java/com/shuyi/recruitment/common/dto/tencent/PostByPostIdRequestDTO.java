package com.shuyi.recruitment.common.dto.tencent;

import lombok.Data;

@Data
public class PostByPostIdRequestDTO {
    private Long timestamp;
    private String postId;
    private String language;
}
