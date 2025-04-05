package com.shuyi.recruitment.repository.sqlite;

import com.shuyi.recruitment.common.util.FileUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        String[] initDdSqlPaths = {
                // "classpath:sqlite/tencent_job/create_tencent_job.ddl",
                // spring init 不支持 trigger，所以把 trigger 单独放这里
                "classpath:sqlite/tencent_job/create_update_timestamp_trigger.ddl"
        };
        for (String ddSqlPath : initDdSqlPaths) {
            String sql = FileUtil.readFile(ddSqlPath);
            jdbcTemplate.execute(sql);
        }
    }
}