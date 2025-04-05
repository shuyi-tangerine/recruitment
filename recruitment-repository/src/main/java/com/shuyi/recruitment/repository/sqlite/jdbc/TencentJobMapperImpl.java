package com.shuyi.recruitment.repository.sqlite.jdbc;

import com.shuyi.recruitment.common.util.FileUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component("tencentJobMapperJdbcImpl")
public class TencentJobMapperImpl {

    private Connection conn;

    public TencentJobMapperImpl() {
        this.initDB();
    }

    /**
     * 做一些初始化的东西，比如创建数据库连接、创建数据库等
     * 学习 JDBC 使用时候的代码，基本不用了，就放着学习使用
     */
    private void initDB() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:recruitment.db");
            this.conn = conn;

            String[] initDdSqlPaths = {
                    // "classpath:sqlite/tencent_job/create_tencent_job.ddl",
                    // "classpath:sqlite/tencent_job/create_update_timestamp_trigger.ddl",
                    // "classpath:sqlite/tencent_job/insert_test_data.sql",
                    // "classpath:sqlite/tencent_job/delete_test_data.sql"
            };
            for (String ddSqlPath : initDdSqlPaths) {
                String sql = FileUtil.readFile(ddSqlPath);
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sql);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
