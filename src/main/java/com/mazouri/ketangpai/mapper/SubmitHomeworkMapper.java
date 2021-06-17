package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.SubmitHomework;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-13
 */
public interface SubmitHomeworkMapper extends BaseMapper<SubmitHomework> {

    List<SubmitHomework> getSubmitHomeworkList(@Param("courseId") String courseId,@Param("userId") String userId);
}
