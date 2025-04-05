package com.shuyi.recruitment.common.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileUtil {

    /**
     * @param classpathBeginPath 形如 classpath:aaa/bbb
     * @return 全部内容
     */
    public static String readFile(String classpathBeginPath) {
        try {
            File file = ResourceUtils.getFile(classpathBeginPath);
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
