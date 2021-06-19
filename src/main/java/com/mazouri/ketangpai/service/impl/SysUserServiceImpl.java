package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.SysRole;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.mapper.SysRoleMapper;
import com.mazouri.ketangpai.mapper.SysUserMapper;
import com.mazouri.ketangpai.service.SysPermissionService;
import com.mazouri.ketangpai.service.SysRolePermissionService;
import com.mazouri.ketangpai.service.SysRoleService;
import com.mazouri.ketangpai.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPermissionService permissionService;


    @Override
    public SysUser selectByEmail(String email) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().eq("email",email));
    }

    @Override
    public Map<String, Object> getUserInfo(String email) {
        System.out.println("userInfo-----------------"+email);
        Map<String, Object> result = new HashMap<>();
        SysUser user = selectByEmail(email);

        //根据用户id获取角色
        List<String> roleList = roleService.selectRoleByEmail(user.getEmail());

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        System.out.println(permissionValueList);

        result.put("id", user.getId());
        result.put("name", user.getUsername());
        result.put("avatar", user.getAvatar());
        result.put("roles", roleList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    @Override
    public List<SysUser> getAllStudentByCourseId(String courseId) {
        return baseMapper.getAllStudentByCourseId(courseId);
    }

    @Override
    public List<SysUser> getAllTeacherByCourseId(String courseId) {
        return baseMapper.getAllTeacherByCourseId(courseId);
    }

    @Override
    public List<SysUser> getReadNoticeUser(String noticeId) {
        return baseMapper.getReadNoticeUser(noticeId);
    }


}
