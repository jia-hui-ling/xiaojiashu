package com.jiahuiling.xiaojiashu.auth.domain.mapper;

import com.jiahuiling.xiaojiashu.auth.domain.dataobject.UserRoleDO;

public interface UserRoleDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRoleDO record);

    int insertSelective(UserRoleDO record);

    UserRoleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleDO record);

    int updateByPrimaryKey(UserRoleDO record);
}