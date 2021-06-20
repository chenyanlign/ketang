package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.AttendUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.AttendUserVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-20
 */
public interface AttendUserMapper extends BaseMapper<AttendUser> {
    List<AttendUserVO> getAllUserByCourseId(String courseId);
}
