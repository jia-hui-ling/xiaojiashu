package com.jiahuiling.xiaojiashu.auth.runner;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiahuiling.framework.jackson.util.JsonUtils;
import com.jiahuiling.xiaojiashu.auth.constant.RedisKeyConstants;
import com.jiahuiling.xiaojiashu.auth.domain.dataobject.PermissionDO;
import com.jiahuiling.xiaojiashu.auth.domain.dataobject.RoleDO;
import com.jiahuiling.xiaojiashu.auth.domain.dataobject.RolePermissionDO;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.PermissionDOMapper;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.RoleDOMapper;
import com.jiahuiling.xiaojiashu.auth.domain.mapper.RolePermissionDOMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author 贾慧玲
 * @Date 2026/9/4 20:16
 * @Description TODO
 */
@Component
@Slf4j
public class PushRolePermissions2RedisRunner implements ApplicationRunner {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private RoleDOMapper roleDOMapper;
    @Resource
    private PermissionDOMapper permissionDOMapper;
    @Resource
    private RolePermissionDOMapper rolePermissionDOMapper;
    // 权限同步标记 Key
    private static final String PUSH_PERMISSION_FLAG = "push.permission.flag";


    @Override
    public void run(ApplicationArguments args) {
        log.info("==> 服务启动，开始同步角色权限数据到 Redis 中...");

        try {
            // 是否能够同步数据: 原子操作，只有在键 PUSH_PERMISSION_FLAG 不存在时，才会设置该键的值为 "1"，并设置过期时间为 1 天
            boolean canPushed = redisTemplate.opsForValue().setIfAbsent(PUSH_PERMISSION_FLAG, "1", 1, TimeUnit.DAYS);

            // 如果无法同步权限数据
            if (!canPushed) {
                log.warn("==> 角色权限数据已经同步至 Redis 中，不再同步...");
                return;
            }
            List<RoleDO> roleDOS = roleDOMapper.selectEnabledList();
            if (CollUtil.isNotEmpty(roleDOS)) {
                List<Long> roleIds = roleDOS.stream().map(RoleDO::getId).toList();
                List<RolePermissionDO> rolePermissionDOS = rolePermissionDOMapper.selectByRoleIds(roleIds);
                Map<Long, List<Long>> roleIdPermissionIdsMap = rolePermissionDOS.stream().collect(
                        Collectors.groupingBy(RolePermissionDO::getRoleId,
                                Collectors.mapping(RolePermissionDO::getPermissionId, Collectors.toList()))
                );
                List<PermissionDO> permissionDOS = permissionDOMapper.selectAppEnabledList();
                Map<Long, PermissionDO> permissionDOMap = permissionDOS.stream().collect(
                        Collectors.toMap(PermissionDO::getId, permissionDO -> permissionDO)
                );

                HashMap<Long, List<PermissionDO>> roleIdPermissionDOMap = Maps.newHashMap();

                roleDOS.forEach(roleDO -> {
                    Long roleId = roleDO.getId();
                    List<Long> permissionIds = roleIdPermissionIdsMap.get(roleId);
                    if (CollUtil.isNotEmpty(permissionIds)) {
                        List<PermissionDO> perDOs = Lists.newArrayList();
                        permissionIds.forEach(permissionId -> {
                            PermissionDO permissionDO = permissionDOMap.get(permissionId);
                            perDOs.add(permissionDO);
                        });
                        roleIdPermissionDOMap.put(roleId, permissionDOS);
                    }
                });
                roleIdPermissionDOMap.forEach((roleId, permissions) -> {
                    String key = RedisKeyConstants.buildRolePermissionsKey(roleId);
                    redisTemplate.opsForValue().set(key, JsonUtils.toJsonString(permissions));
                });
            }
            log.info("==> 服务启动，成功同步角色权限数据到 Redis 中...");

        } catch (Exception e) {
            log.error("==> 同步角色权限数据到 Redis 中失败: ", e);
        }


    }
}
