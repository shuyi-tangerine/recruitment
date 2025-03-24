package com.shuyi.recruitment.common.dto.tencent;

import lombok.Data;

import java.util.List;

@Data
public class PostQueryRequestDTO {
    private Long timestamp;
    private String countryId;
    private List<Integer> cityId;
    private List<Integer> bgIds;
    private Integer productId;
    private Integer categoryId;
    private Integer parentCategoryId;
    private Integer attrId;
    private String keyword;
    private Integer pageIndex;
    private Integer pageSize;
    private String language;
    private String area;
}
