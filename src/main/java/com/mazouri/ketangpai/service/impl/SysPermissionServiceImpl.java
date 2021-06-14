package com.mazouri.ketangpai.service.impl;

import com.mazouri.ketangpai.entity.SysPermission;
import com.mazouri.ketangpai.mapper.SysPermissionMapper;
import com.mazouri.ketangpai.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        return baseMapper.selectPermissionValueByUserId(id);
    }
}
