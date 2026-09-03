package com.jiahuiling.xiaojiashu.auth.domain.mapper;

import com.jiahuiling.xiaojiashu.auth.domain.dataobject.UserDO;

/**
 * @Author 贾慧玲
 * @Date 2026/9/3 11:23
 * @Description TODO
 */
public interface UserDOMapper {

    /**
     * 根据主键 ID 查询
     * @param id
     * @return
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * 根据主键 ID 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(UserDO record);

    /**
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserDO record);
}
