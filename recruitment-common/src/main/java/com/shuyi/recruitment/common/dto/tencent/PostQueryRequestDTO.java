package com.shuyi.recruitment.common.dto.tencent;

import lombok.Data;

import java.util.List;

@Data
public class PostQueryRequestDTO {
    private Long timestamp;
    private List<Integer> countryId;
    private List<Integer> cityId;
    private List<Integer> bgIds;
    private List<Integer> productId;
    private List<Integer> categoryId;
    private List<Integer> parentCategoryId;
    private List<Integer> attrId;
    private String keyword;
    private Integer pageIndex;
    private Integer pageSize;
    private String language;
    private String area;
}
