package com.shuyi.recruitment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IsDeletedEnum {
    NOT_DELETED(0, "未删除"),
    DELETED(1, "删除"),

    ;

    private final int code;
    private final String description;
}
