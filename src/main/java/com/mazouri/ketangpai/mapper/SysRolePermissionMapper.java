package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.dto.RolesPermission;

import java.util.List;

/**
 * <p>
 * 角色权限 Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    List<RolesPermission> getPermissionRoles();
}
