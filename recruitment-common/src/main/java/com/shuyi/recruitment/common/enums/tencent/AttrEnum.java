package com.shuyi.recruitment.common.enums.tencent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AttrEnum {

    SOCIAL(1, "社招", ""),
    CAMPUS_FRESH_GRADUATE(2, "校招应届生", ""),
    CAMPUS_TRAINEE(3, "校招实习生", ""),

    ;

    private final Integer code;
    private final String name;
    private final String description;
}
