package com.shuyi.recruitment.common.dto.tencent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PostQueryResponseDataDTO {
    @JsonProperty("Count")
    private Integer count;
    @JsonProperty("Posts")
    private List<PostDTO> posts;
}
