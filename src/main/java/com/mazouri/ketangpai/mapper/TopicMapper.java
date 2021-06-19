package com.mazouri.ketangpai.mapper;

import com.mazouri.ketangpai.entity.Topic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mazouri.ketangpai.entity.vo.TopicVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface TopicMapper extends BaseMapper<Topic> {

    List<TopicVO> getAllTopic(String courseId);

    TopicVO getTopicById(String id);
}
