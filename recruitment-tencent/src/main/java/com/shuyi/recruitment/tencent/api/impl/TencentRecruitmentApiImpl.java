package com.shuyi.recruitment.tencent.api.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shuyi.recruitment.common.dto.tencent.PostByPostIdRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryRequestDTO;
import com.shuyi.recruitment.common.dto.tencent.PostQueryResponseDataDTO;
import com.shuyi.recruitment.common.dto.tencent.ResponseDTO;
import com.shuyi.recruitment.common.enums.tencent.LanguageEnum;
import com.shuyi.recruitment.common.util.TencentUtil;
import com.shuyi.recruitment.tencent.api.TencentRecruitmentApi;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class TencentRecruitmentApiImpl implements TencentRecruitmentApi {
    @Override
    public ResponseDTO<PostQueryResponseDataDTO> postQuery(String url) {
        int retryTimes = 2;
        for (int i = 0; i <= retryTimes; i++) {
            try {
                // 超时单位：毫秒
                Document document = Jsoup.connect(url).ignoreContentType(true).headers(this.getHeaders()).timeout(5000).get();
                System.out.println("[postQuery] url: " + url);
                return TencentUtil.parse(document.text(), new TypeReference<>() {});
            } catch (IOException e) {
                if (i >= retryTimes) {
                    throw new RuntimeException(e);
                } else {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("no query");
    }

    @Override
    public ResponseDTO<PostQueryResponseDataDTO> postQuery(PostQueryRequestDTO requestDTO) {
        String targetUrl = TencentUtil.buildPostQueryUrl(requestDTO);
        return this.postQuery(targetUrl);
    }


    @Override
    public PostQueryResponseDataDTO queryPostListPage(PostQueryRequestDTO requestDTO) {
        ResponseDTO<PostQueryResponseDataDTO> responseDTO = this.postQuery(requestDTO);
        TencentUtil.checkResponse(responseDTO);
        return responseDTO.getData();
    }

    @Override
    public ResponseDTO<PostDTO> postByPostId(String url) {
        int retryTimes = 2;
        for (int i = 0; i <= retryTimes; i++) {
            try {
                // 超时单位：毫秒
                Document document = Jsoup.connect(url).ignoreContentType(true).headers(this.getHeaders()).timeout(5000).get();
                return TencentUtil.parse(document.text(), new TypeReference<>() {});
            } catch (IOException e) {
                if (i >= retryTimes) {
                    throw new RuntimeException(e);
                } else {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("no query");
    }

    @Override
    public ResponseDTO<PostDTO> postByPostId(PostByPostIdRequestDTO requestDTO) {
        String targetUrl = TencentUtil.buildPostByPostIdUrl(requestDTO);
        return this.postByPostId(targetUrl);
    }

    @Override
    public PostDTO queryPostByPostId(String postId) {
        PostByPostIdRequestDTO requestDTO = new PostByPostIdRequestDTO();
        requestDTO.setPostId(postId);
        requestDTO.setTimestamp(System.currentTimeMillis());
        requestDTO.setLanguage(LanguageEnum.ZH_CN.getName());

        ResponseDTO<PostDTO> responseDTO = this.postByPostId(requestDTO);
        TencentUtil.checkResponse(responseDTO);

        return responseDTO.getData();
    }

    private Map<String, String> getHeaders() {
        return Map.of(
                "accept", "application/json, text/plain, */*",
                "sec-ch-ua", "\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"",
                "sec-ch-ua-mobile", "?0",
                "sec-ch-ua-platform", "\"macOS\"",
                "sec-fetch-dest", "empty",
                "sec-fetch-mode", "cors",
                "sec-fetch-site", "same-origin",
                "user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36"
        );
    }
}