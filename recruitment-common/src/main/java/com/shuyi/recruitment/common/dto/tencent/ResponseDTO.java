package com.shuyi.recruitment.common.dto.tencent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseDTO<T> {
    @JsonProperty("Code")
    private Integer code;

    @JsonProperty("Data")
    private T data;
}
