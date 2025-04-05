package com.shuyi.recruitment.repository.dao;

import com.shuyi.recruitment.common.entity.TencentJobDO;

public interface TencentJobDAO {

    /**
     * upsert
     * @param req 参数
     * @return 插入后的数据
     */
    TencentJobDO upsert(TencentJobDO req);

    /**
     * 按照 id、postId 等业务唯一键查询数据，一般只有一条，不返回软删除的数据
     * @param req 参数
     * @return 数据
     */
    TencentJobDO selectOne(TencentJobDO req);

    /**
     * 按照 id 唯一键查询数据，可返回已经软删除的数据
     * @param req 参数
     * @return 数据
     */
    TencentJobDO selectOneNeedDeleted(TencentJobDO req);

    /**
     * 根据 postId 获取未软删除的数据
     * @param postId
     * @return
     */
    TencentJobDO selectByPostId(String postId);

    /**
     * 根据 id 获取未软删除的数据
     * @param id
     * @return
     */
    TencentJobDO selectById(long id);

    /**
     * 根据 id 获取数据，未软删除也可能返回
     * @param id
     * @return
     */
    TencentJobDO selectByIdNeedDeleted(long id);

}
