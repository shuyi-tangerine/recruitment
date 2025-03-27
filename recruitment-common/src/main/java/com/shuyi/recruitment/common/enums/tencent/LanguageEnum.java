package com.shuyi.recruitment.common.enums.tencent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LanguageEnum {

    ZH_CN("zh-cn", ""),

    ;

    private final String name;
    private final String description;
}
