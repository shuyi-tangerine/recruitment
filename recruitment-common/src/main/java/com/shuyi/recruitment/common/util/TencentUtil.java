package com.shuyi.recruitment.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuyi.recruitment.common.dto.tencent.ResponseDTO;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class TencentUtil {
    public static final int DEFAULT_PAGE_SIZE = 10;

    private static final String POST_QUERY_BASE_URL = "https://careers.tencent.com/tencentcareer/api/post/Query";
    private static final String POST_BY_POST_ID_BASE_URL = "https://careers.tencent.com/tencentcareer/api/post/ByPostId";

    private static final int SUCCESS_CODE = 200;


    private static final String DATE_TIME_PATTERN = "yyyy年MM月dd日";

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
        try {
            return ZonedDateTime.of(
                    LocalDateTime.parse(raw, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.getDefault())),
                    ZoneId.systemDefault()
            ).toEpochSecond();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

}
