package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.Attend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.AttendVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-20
 */
public interface AttendMapper extends BaseMapper<Attend> {

    List<AttendVO> getAllAttendByCourseId(String courseId);
}
