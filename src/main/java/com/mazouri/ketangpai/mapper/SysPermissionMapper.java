package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<String> selectPermissionValueByUserId(String id);
}
