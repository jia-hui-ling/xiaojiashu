package com.jiahuiling.xiaojiashu.user.relation.biz.domain.mapper;

import com.jiahuiling.xiaojiashu.user.relation.biz.domain.dataobject.FansDO;
import io.lettuce.core.dynamic.annotation.Param;

public interface FansDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FansDO record);

    int insertSelective(FansDO record);

    FansDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FansDO record);

    int updateByPrimaryKey(FansDO record);

    int deleteByUserIdAndFansUserId(@Param("userId") Long userId,
                                         @Param("unfollowUserId") Long unfollowUserId);
}