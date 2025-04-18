package com.shuyi.recruitment.repository.dao.impl;

import com.shuyi.recruitment.common.entity.TencentJobDO;
import com.shuyi.recruitment.common.enums.IsDeletedEnum;
import com.shuyi.recruitment.common.util.TencentUtil;
import com.shuyi.recruitment.repository.mapper.TencentJobMapper;
import com.shuyi.recruitment.repository.dao.TencentJobDAO;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TencentJobDaoImpl implements TencentJobDAO {

    private final TencentJobMapper tencentJobMapper;

    public TencentJobDaoImpl(TencentJobMapper tencentJobMapper) {
        this.tencentJobMapper = tencentJobMapper;
    }

    @Override
    public TencentJobDO upsert(TencentJobDO req) {
        if (Objects.isNull(req)) {
            return null;
        }

        TencentJobDO tencentJobDO = this.selectByPostId(req.getPostID());
        if (tencentJobDO == null) {
            this.tencentJobMapper.insertOne(req);
            return this.selectById(req.getId());
        }

        req.setId(tencentJobDO.getId());
        int updateAffectedRows = this.tencentJobMapper.updateById(req);
        return this.selectById(tencentJobDO.getId());
    }

    @Override
    public TencentJobDO selectOne(TencentJobDO req) {
        TencentJobDO tencentJobDO = this.tencentJobMapper.selectOne(TencentJobDO.builder().
                id(req.getId()).
                postID(req.getPostID()).
                isDeleted(IsDeletedEnum.NOT_DELETED.getCode()).
                build());
        this.rebuildCreatedAtUpdatedAt(tencentJobDO);
        return tencentJobDO;
    }

    @Override
    public TencentJobDO selectOneNeedDeleted(TencentJobDO req) {
        TencentJobDO tencentJobDO = this.tencentJobMapper.selectOne(TencentJobDO.builder().
                id(req.getId()).
                postID(req.getPostID()).
                isDeleted(null).
                build());
        this.rebuildCreatedAtUpdatedAt(tencentJobDO);
        return tencentJobDO;
    }

    @Override
    public TencentJobDO selectByPostId(String postId) {
        return this.selectOne(TencentJobDO.builder().
                postID(postId).
                isDeleted(IsDeletedEnum.NOT_DELETED.getCode()).
                build());
    }

    @Override
    public TencentJobDO selectById(long id) {
        return this.selectOne(TencentJobDO.builder().
                id(id).
                isDeleted(IsDeletedEnum.NOT_DELETED.getCode()).
                build());
    }

    @Override
    public TencentJobDO selectByIdNeedDeleted(long id) {
        return this.selectOneNeedDeleted(TencentJobDO.builder().
                id(id).
                build());
    }

    private void rebuildCreatedAtUpdatedAt(TencentJobDO tencentJobDO) {
        if (Objects.nonNull(tencentJobDO)) {
            tencentJobDO.setCreatedAt(TencentUtil.rebuildLocalZoneTimestamp(tencentJobDO.getCreatedAt()));
            tencentJobDO.setUpdatedAt(TencentUtil.rebuildLocalZoneTimestamp(tencentJobDO.getUpdatedAt()));
        }
    }
}
