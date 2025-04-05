
-- 创建更新时间的触发器
CREATE TRIGGER IF NOT EXISTS update_timestamp
    AFTER UPDATE ON tencent_job
    FOR EACH ROW
BEGIN
    UPDATE tencent_job SET updated_at = CURRENT_TIMESTAMP WHERE id = OLD.id;
END;

