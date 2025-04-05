package com.shuyi.recruitment.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QueryExecMode {
    NOT_EXIST_INSERT(0, "数据库不存在的时候，才会插入"),
    ALL_UPSERT(1, "所有的数据，数据库中存在则更新，不存在则插入"),
    AFTER_LONG_WAIT_UPDATE(2, "有段时间没有更新了则更新一下"),

    ;

    private final int code;
    private final String description;
}
