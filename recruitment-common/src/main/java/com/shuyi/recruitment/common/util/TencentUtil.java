package com.shuyi.recruitment.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuyi.recruitment.common.dto.tencent.PostDTO;
import com.shuyi.recruitment.common.dto.tencent.ResponseDTO;
import com.shuyi.recruitment.common.entity.TencentJobDO;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class TencentUtil {
    public static final int DEFAULT_PAGE_SIZE = 10;

    private static final String POST_QUERY_BASE_URL = "https://careers.tencent.com/tencentcareer/api/post/Query";
    private static final String POST_BY_POST_ID_BASE_URL = "https://careers.tencent.com/tencentcareer/api/post/ByPostId";

    private static final int SUCCESS_CODE = 200;


    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd";
    private static final String[] DATE_TIME_PATTERNS = {"yyyy年MM月dd日", "yyyy-MM-dd"};

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 忽略对象中没有定义的字段
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T parse(String jsonString, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String buildUrl(String baseUrl, Object requestDTO) {
        // 反射拿一下字段和值
        StringBuilder sb = new StringBuilder();
        for (Field field : requestDTO.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                Object fieldValue = field.get(requestDTO);
                if (Objects.isNull(fieldValue)) {
                    continue;
                }

                if (!sb.isEmpty()) {
                    sb.append("&");
                }

                if (List.class.isAssignableFrom(field.getType())) {
                    // 列表，处理成 a,b,c这种格式
                    List<String> strList = ((List<?>) fieldValue).stream().map(Object::toString).toList();
                    sb.append(String.format("%s=%s", fieldName, String.join(",", strList)));
                } else {
                    sb.append(String.format("%s=%s", fieldName, fieldValue));
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return String.format("%s?%s", baseUrl, sb);
    }

    public static <T> String buildPostQueryUrl(T requestDTO) {
        return buildUrl(POST_QUERY_BASE_URL, requestDTO);
    }

    public static <T> String buildPostByPostIdUrl(T requestDTO) {
        return buildUrl(POST_BY_POST_ID_BASE_URL, requestDTO);
    }

    public static <T> boolean isSuccessCode(ResponseDTO<T> responseDTO) {
        return Objects.nonNull(responseDTO) && Objects.equals(SUCCESS_CODE, responseDTO.getCode());
    }

    public static Long parseUnixSeconds(String raw) {
        if (Objects.isNull(raw) || raw.isBlank()) {
            return null;
        }

        return parseTimestamp(raw).toInstant().getEpochSecond();
    }

    public static Timestamp parseTimestamp(String raw) {
        if (Objects.isNull(raw) || raw.isBlank()) {
            return null;
        }

        for (int i=0; i<DATE_TIME_PATTERNS.length; i++) {
            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERNS[i], Locale.CHINA);
                LocalDate localDate = LocalDate.parse(raw, formatter);

                return Timestamp.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            } catch (Exception e) {
                if (i + 1 >= DATE_TIME_PATTERNS.length) {
                    throw e;
                }
            }
        }
        throw new RuntimeException("no pattern can parse date time: " + raw);
    }

    public static <T> void checkResponse(ResponseDTO<T> responseDTO) {
        if (TencentUtil.isSuccessCode(responseDTO)) {
            return;
        }

        Integer code = null;
        if (Objects.nonNull(responseDTO)) {
            code = responseDTO.getCode();
        }

        throw new RuntimeException("query fail, code:" + code);
    }

    public static TencentJobDO postDtoToTencentJobDO(PostDTO postDTO) {
        TencentJobDO tencentJobDO = TencentJobDO.builder().build();

        tencentJobDO.setPostID(postDTO.getPostId());
        tencentJobDO.setRecruitPostID(postDTO.getRecruitPostID());
        tencentJobDO.setRecruitPostName(postDTO.getRecruitPostName());
        tencentJobDO.setLocationName(postDTO.getLocationName());
        tencentJobDO.setBgName(postDTO.getBgName());
        tencentJobDO.setOuterPostTypeID(postDTO.getOuterPostTypeID());
        tencentJobDO.setCategoryName(postDTO.getCategoryName());
        tencentJobDO.setResponsibility(postDTO.getResponsibility());
        tencentJobDO.setLastUpdateTime(parseTimestamp(postDTO.getLastUpdateTime()));
        tencentJobDO.setPostURL(postDTO.getPostURL());
        tencentJobDO.setImportantItem(postDTO.getImportantItem());
        tencentJobDO.setRequireWorkYearsName(postDTO.getRequireWorkYearsName());

        return tencentJobDO;
    }

    /**
     * 主要是针对 SQLite 时间戳存储做的一些工作
     * @param confusedLocalZoneAndUtcTimestamp 把北京当前UTC时区的错误 ts，如北京2025-04-05 00:00:00。正确的应该是UTC2025-04-05 00:00:00或者北京2025-04-05 08:00:00
     * @return 返回正确的时间
     */
    public static Timestamp rebuildLocalZoneTimestamp(Timestamp confusedLocalZoneAndUtcTimestamp) {
        if (Objects.isNull(confusedLocalZoneAndUtcTimestamp)) {
            return null;
        }

        // 先算出和 UTC 的偏移量，然后给错误地时间加上即可
        int offsetMS = TimeZone.getDefault().getOffset(System.currentTimeMillis());
        Instant instant = confusedLocalZoneAndUtcTimestamp.toInstant().plusMillis(offsetMS);
        return Timestamp.from(instant);
    }
}
