package com.shuyi.recruitment.repository.mapper;

import com.shuyi.recruitment.common.entity.TencentJobDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TencentJobMapper {
    /**
     * @param tencentJobDO 插入一条记录，只操作不为空的字段
     * @return 影响行数，id 会在 tencentJobDO.getId() 中
     */
    long insertOne(TencentJobDO tencentJobDO);

    /**
     * @param tencentJobDO 利用 id 更新不为空的字段
     * @return 影响行数
     */
    int updateById(TencentJobDO tencentJobDO);

    /**
     * @param req 通过id、 postId 等比较唯一的属性查询，只返回一条
     * @return 数据
     */
    TencentJobDO selectOne(TencentJobDO req);

}
