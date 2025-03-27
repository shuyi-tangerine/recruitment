package com.shuyi.recruitment.common.enums.tencent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryEnum {
    FINANCE(40007, "财务", ""),

    // 技术
    TECH_RESEARCH_DEV(40001001, "技术研发类", ""),
    QUALITY_CONTROL(40001002, "质量管理类", ""),

    ;

    private final Integer code;
    private final String name;
    private final String description;
}
