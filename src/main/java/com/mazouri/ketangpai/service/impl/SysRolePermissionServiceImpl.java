package com.mazouri.ketangpai.service.impl;

import com.mazouri.ketangpai.entity.SysRolePermission;
import com.mazouri.ketangpai.entity.dto.RolesPermission;
import com.mazouri.ketangpai.mapper.SysRolePermissionMapper;
import com.mazouri.ketangpai.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public HashMap<String, List<String>> getPermissionRoles() {
        List<RolesPermission> permissionRoles = baseMapper.getPermissionRoles();
        HashMap<String, List<String>> map = new HashMap<>();
        for (RolesPermission permissionRole : permissionRoles) {
            if (map.containsKey(permissionRole.getPermission())) {
                map.get(permissionRole.getPermission()).add(permissionRole.getRole());
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(permissionRole.getRole());
                map.put(permissionRole.getPermission(), list);
            }
        }
        return map;
    }
}
