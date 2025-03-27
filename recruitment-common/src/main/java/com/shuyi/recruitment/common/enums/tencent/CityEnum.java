package com.shuyi.recruitment.common.enums.tencent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CityEnum {

    SHENZHEN(1, "深圳", ""),
    GUANGZHOU(5, "广州", ""),

    ;

    private final Integer code;
    private final String name;
    private final String description;
}
