package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户的邮箱查找该用户具有的角色
     * @param email
     * @return
     */
    List<String> selectRoleByEmail(String email);
}
