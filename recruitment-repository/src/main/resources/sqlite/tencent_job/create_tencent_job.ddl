
CREATE TABLE IF NOT EXISTS `tencent_job`(
    `id` integer PRIMARY KEY AUTOINCREMENT,
    `post_id` varchar(32) NOT NULL DEFAULT '',
    `recruit_post_id` bigint NOT NULL DEFAULT 0,
    `recruit_post_name` varchar(256) NOT NULL DEFAULT '',
    `location_name` varchar(32) NOT NULL DEFAULT '',
    `bg_name` varchar(16) NOT NULL DEFAULT '',
    `outer_post_type_id` varchar(32) NOT NULL DEFAULT '',
    `category_name` varchar(32) NOT NULL DEFAULT '',
    `responsibility` text DEFAULT NULL,
    `last_update_time` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00',
    `post_url` varchar(32) NOT NULL DEFAULT '',
    `important_item` text DEFAULT NULL,
    `require_work_years_name` varchar(32) NOT NULL DEFAULT '',

    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` varchar(32) NOT NULL DEFAULT '',
    `updated_by` varchar(32) NOT NULL DEFAULT '',
    `status` tinyint NOT NULL DEFAULT 0,
    `extra` text DEFAULT NULL,
    `is_deleted` tinyint NOT NULL DEFAULT 0
);

-- 索引
CREATE INDEX IF NOT EXISTS `idx_lut` ON `tencent_job` (`last_update_time`);

-- 创建更新时间的触发器
CREATE TRIGGER IF NOT EXISTS update_timestamp
    AFTER UPDATE ON tencent_job
    FOR EACH ROW
BEGIN
    UPDATE tencent_job SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;
END;

