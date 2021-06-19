package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.vo.TopicVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
public interface TopicService extends IService<Topic> {

    List<TopicVO> getTopicList(String courseId);

    TopicVO getTopicById(String id);

    Boolean updateTopOrEssence(String topicId, String type);
}
