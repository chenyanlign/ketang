package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SysPermissionService extends IService<SysPermission> {
    List<String> selectPermissionValueByUserId(String id);
}
