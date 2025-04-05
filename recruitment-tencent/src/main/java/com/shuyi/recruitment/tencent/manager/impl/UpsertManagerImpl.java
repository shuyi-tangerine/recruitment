package com.shuyi.recruitment.tencent.manager.impl;

import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryResponseDataDTO;
import com.shuyi.recruitment.common.entity.TencentJobDO;
import com.shuyi.recruitment.common.enums.tencent.AreaEnum;
import com.shuyi.recruitment.common.enums.tencent.AttrEnum;
import com.shuyi.recruitment.common.enums.tencent.CategoryEnum;
import com.shuyi.recruitment.common.enums.tencent.CityEnum;
import com.shuyi.recruitment.common.enums.tencent.LanguageEnum;
import com.shuyi.recruitment.common.util.TencentUtil;
import com.shuyi.recruitment.common.util.ThreadUtil;
import com.shuyi.recruitment.repository.dao.TencentJobDAO;
import com.shuyi.recruitment.tencent.api.TencentRecruitmentApi;
import com.shuyi.recruitment.tencent.manager.UpsertManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class UpsertManagerImpl implements UpsertManager {

    // 防止数据问题导致死循环
    private static final int MAX_PAGE = 500;
    // 防止爬得过快
    private static final int SLEEP_START_MILLIS = 500;
    private static final int SLEEP_END_MILLIS = 5000;

    @Autowired
    private TencentRecruitmentApi tencentRecruitmentApi;

    @Autowired
    private TencentJobDAO tencentJobDAO;

    @Override
    public void upsertByStartTime(long startUnixSeconds) {
        // 先获取页数数据，拿到所有需要的 postId
        Set<String> postIds =  new HashSet<>();

        int pageIndex = 1;
        PostQueryRequestDTO requestDTO = this.buildDefaultPostQueryRequestDTO(pageIndex);

        while (true) {
            requestDTO.setPageIndex(pageIndex);

            ++pageIndex;

            PostQueryResponseDataDTO postQueryResponseDataDTO = tencentRecruitmentApi.queryPostListPage(requestDTO);

            // 没有数据了，跳出
            if (Objects.isNull(postQueryResponseDataDTO) || Objects.isNull(postQueryResponseDataDTO.getPosts()) || postQueryResponseDataDTO.getPosts().isEmpty()) {
                break;
            }

            // 达到时间阈值了，也终止
            boolean tsEnd = false;
            for(PostDTO post : postQueryResponseDataDTO.getPosts()) {
                Long lastUpdateSec = TencentUtil.parseUnixSeconds(post.getLastUpdateTime());
                if (Objects.isNull(lastUpdateSec)) {
                    tsEnd = true;
                    break;
                }

                if (startUnixSeconds > lastUpdateSec) {
                    tsEnd = true;
                    break;
                }

                postIds.add(post.getPostId());
            }

            if (tsEnd || pageIndex >= MAX_PAGE) {
                break;
            }

            ThreadUtil.sleepRandomMillis(SLEEP_START_MILLIS, SLEEP_END_MILLIS, "");
        }

        // 获取数据详情、插入到数据库中
        this.upsertByPostIds(postIds.toArray(new String[0]));
    }

    @Override
    public void upsertAmountPost(int amount) {
        // 先获取页数数据，拿到所有需要的 postId
        Set<String> postIds =  new HashSet<>();

        int pageIndex = 1;
        PostQueryRequestDTO requestDTO = this.buildDefaultPostQueryRequestDTO(pageIndex);

        while (true) {
            requestDTO.setPageIndex(pageIndex);
            requestDTO.setTimestamp(System.currentTimeMillis());

            ++pageIndex;

            PostQueryResponseDataDTO postQueryResponseDataDTO = tencentRecruitmentApi.queryPostListPage(requestDTO);

            // 没有数据了，跳出
            if (Objects.isNull(postQueryResponseDataDTO) || Objects.isNull(postQueryResponseDataDTO.getPosts()) || postQueryResponseDataDTO.getPosts().isEmpty()) {
                break;
            }

            System.out.printf("page(%d) list dto size:%d \n", pageIndex, postQueryResponseDataDTO.getPosts().size());

            // 达到需要的数据量级了也停止
            for(PostDTO post : postQueryResponseDataDTO.getPosts()) {
                postIds.add(post.getPostId());
                if (postIds.size() >= amount) {
                    break;
                }
            }

            if (postIds.size() >= amount || pageIndex >= MAX_PAGE) {
                break;
            }

            ThreadUtil.sleepRandomMillis(SLEEP_START_MILLIS, SLEEP_END_MILLIS, "");
        }

        // 获取数据详情、插入到数据库中
        this.upsertByPostIds(postIds.toArray(new String[0]));
    }

    @Override
    public void upsertByPostId(String postId) {
        PostDTO postDTO = this.tencentRecruitmentApi.queryPostByPostId(postId);
        if (Objects.isNull(postDTO)) {
            return;
        }

        TencentJobDO tencentJobDO = this.tencentJobDAO.upsert(TencentUtil.postDtoToTencentJobDO(postDTO));
        System.out.println("insert dto: " + postDTO + ", do: " + tencentJobDO);

        ThreadUtil.sleepRandomMillis(SLEEP_START_MILLIS, SLEEP_END_MILLIS, "");
    }

    @Override
    public void upsertByPostIds(String[] postIds) {
        if (Objects.isNull(postIds)) {
            return;
        }

        for (String postId : postIds) {
            this.upsertByPostId(postId);
        }
    }

    private PostQueryRequestDTO buildDefaultPostQueryRequestDTO(int pageIndex) {
        PostQueryRequestDTO requestDTO = new PostQueryRequestDTO();
        requestDTO.setTimestamp(System.currentTimeMillis());
        requestDTO.setCategoryId(List.of(CategoryEnum.FINANCE.getCode(), CategoryEnum.TECH_RESEARCH_DEV.getCode()));
        requestDTO.setAttrId(List.of(AttrEnum.SOCIAL.getCode(), AttrEnum.CAMPUS_FRESH_GRADUATE.getCode(), AttrEnum.CAMPUS_TRAINEE.getCode()));
        requestDTO.setCityId(List.of(CityEnum.SHENZHEN.getCode(), CityEnum.BEIJING.getCode(), CityEnum.GUANGZHOU.getCode()));
        requestDTO.setPageSize(TencentUtil.DEFAULT_PAGE_SIZE);
        requestDTO.setLanguage(LanguageEnum.ZH_CN.getName());
        requestDTO.setArea(AreaEnum.CN.getName());
        return requestDTO;
    }

}
