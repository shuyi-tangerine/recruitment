package com.shuyi.recruitment.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TencentJobDO {
    private Long id;

    private String postID;
    private Long recruitPostID;
    private String recruitPostName;
    private String locationName;
    private String bgName;
    private String outerPostTypeID;
    private String categoryName;
    private String responsibility;
    private Timestamp lastUpdateTime;
    private String postURL;
    private String importantItem;
    private String requireWorkYearsName;

    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;
    private String updatedBy;
    private Integer status;
    private String extra;
    private Integer isDeleted;
}