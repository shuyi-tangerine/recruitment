package com.shuyi.recruitment.common.dto.tencent;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostDTO {
    @JsonProperty("PostId")
    private String postId;

    @JsonProperty("RecruitPostId")
    private Integer recruitPostID;

    @JsonProperty("RecruitPostName")
    private String recruitPostName;

    @JsonProperty("LocationId")
    private Integer locationID;

    @JsonProperty("LocationName")
    private String locationName;

    @JsonProperty("BGId")
    private Integer bgId;

    @JsonProperty("BGName")
    private String bgName;

    @JsonProperty("ComCode")
    private String comCode;

    @JsonProperty("ComName")
    private String comName;

    @JsonProperty("OuterPostTypeID")
    private String outerPostTypeID;

    @JsonProperty("CategoryName")
    private String categoryName;

    @JsonProperty("ProductName")
    private String productName;

    @JsonProperty("Responsibility")
    private String responsibility;

    @JsonProperty("Requirement")
    private String requirement;

    @JsonProperty("LastUpdateTime")
    private String lastUpdateTime;

    @JsonProperty("PostURL")
    private String postURL;

    @JsonProperty("SourceID")
    private Integer sourceID;

    @JsonProperty("IsCollect")
    private Boolean isCollect;

    @JsonProperty("PostLightItem")
    private String postLightItem;

    @JsonProperty("ImportantItem")
    private String importantItem;

    @JsonProperty("Introduction")
    private String introduction;

    @JsonProperty("DepartmentIntroduction")
    private String departmentIntroduction;

    @JsonProperty("RequireWorkYearsName")
    private String requireWorkYearsName;
}