package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SysUserService extends IService<SysUser> {

    SysUser selectByEmail(String email);

    Map<String, Object> getUserInfo(String username);

    List<SysUser> getAllStudentByCourseId(String courseId);

    List<SysUser> getAllTeacherByCourseId(String courseId);
}
