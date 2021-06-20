package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getAllStudentByCourseId(String courseId);

    List<SysUser> getAllTeacherByCourseId(String courseId);

    List<SysUser> getReadNoticeUser(String noticeId);

    List<SysUser> getStudentsCondition(@Param("username") String username,@Param("account")  String account,@Param("courseId")  String courseId);
}
