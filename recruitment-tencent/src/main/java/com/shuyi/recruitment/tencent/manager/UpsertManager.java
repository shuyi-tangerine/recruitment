package com.shuyi.recruitment.tencent.manager;

import com.shuyi.recruitment.common.enums.QueryExecMode;

public interface UpsertManager {

    /*
     * onlyHandleAfterUnixSeconds: 只取 unix ts sec 之后的数据
     */
    void upsertByStartTime(long startUnixSeconds);

    /*
     * upsert 指定条数
     */
    void upsertAmountPost(int amount, QueryExecMode queryExecMode);

    /*
     * upsert 单条数据
     */
    void upsertByPostId(String postId);

    /*
     * upsert 多条数据
     */
    void upsertByPostIds(String[] postIds);

}
