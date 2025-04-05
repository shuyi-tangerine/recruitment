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
        String[] sqlList = {
                "CREATE TABLE IF NOT EXISTS `tencent_job`(\n" +
                        "    `id` integer PRIMARY KEY AUTOINCREMENT,\n" +
                        "    `post_id` varchar(32) NOT NULL DEFAULT '',\n" +
                        "    `recruit_post_id` bigint NOT NULL DEFAULT 0,\n" +
                        "    `recruit_post_name` varchar(256) NOT NULL DEFAULT '',\n" +
                        "    `location_name` varchar(32) NOT NULL DEFAULT '',\n" +
                        "    `bg_name` varchar(16) NOT NULL DEFAULT '',\n" +
                        "    `outer_post_type_id` varchar(32) NOT NULL DEFAULT '',\n" +
                        "    `category_name` varchar(32) NOT NULL DEFAULT '',\n" +
                        "    `responsibility` text DEFAULT NULL,\n" +
                        "    `last_update_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00',\n" +
                        "    `post_url` varchar(32) NOT NULL DEFAULT '',\n" +
                        "    `important_item` text DEFAULT NULL,\n" +
                        "    `require_work_years_name` varchar(32) NOT NULL DEFAULT '',\n" +
                        "\n" +
                        "    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                        "    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                        "    `created_by` varchar(32) NOT NULL DEFAULT '',\n" +
                        "    `updated_by` varchar(32) NOT NULL DEFAULT '',\n" +
                        "    `status` tinyint NOT NULL DEFAULT 0,\n" +
                        "    `extra` text DEFAULT NULL,\n" +
                        "    `is_deleted` tinyint NOT NULL DEFAULT 0\n" +
                        ");",
                "CREATE INDEX IF NOT EXISTS `idx_lut` ON `tencent_job` (`last_update_time`);",
                "CREATE TRIGGER IF NOT EXISTS update_timestamp\n" +
                        "    AFTER UPDATE ON tencent_job\n" +
                        "    FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "    UPDATE tencent_job SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;\n" +
                        "END;"
        };
        for (String sql : sqlList) {
            jdbcTemplate.execute(sql);
        }
    }
}