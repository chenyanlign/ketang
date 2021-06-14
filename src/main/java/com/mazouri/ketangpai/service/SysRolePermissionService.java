package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.dto.RolesPermission;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    HashMap<String, List<String>> getPermissionRoles();
}
