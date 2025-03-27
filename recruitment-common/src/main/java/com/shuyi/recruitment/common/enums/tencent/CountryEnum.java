package com.shuyi.recruitment.common.enums.tencent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CountryEnum {

    CHINA(1, "中国", ""),

    ;

    private final Integer code;
    private final String name;
    private final String description;
}
