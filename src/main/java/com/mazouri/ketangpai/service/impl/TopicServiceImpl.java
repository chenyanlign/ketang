package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.Comment;
import com.mazouri.ketangpai.entity.Topic;
import com.mazouri.ketangpai.entity.vo.TopicVO;
import com.mazouri.ketangpai.mapper.TopicMapper;
import com.mazouri.ketangpai.service.CommentService;
import com.mazouri.ketangpai.service.SysUserService;
import com.mazouri.ketangpai.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.Authorization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
    @Autowired
    private CommentService commentService;

    @Autowired
    private SysUserService userService;

    @Override
    public List<TopicVO> getTopicList(String courseId) {
        List<TopicVO> topicList = baseMapper.getAllTopic(courseId);
        topicList.forEach(topicVO -> topicVO.setCommentNum(commentService.count(new QueryWrapper<Comment>()
                .eq("topic_id", courseId))));
        return topicList;
    }

    @Override
    public TopicVO getTopicById(String id) {
        return baseMapper.getTopicById(id);
    }

    @Override
    public Boolean updateTopOrEssence(String topicId, String type) {
        switch (type) {
            case "置顶":
                baseMapper.updateById(new Topic().setId(topicId).setIsTop(1));
                break;
            case "取消置顶":
                baseMapper.updateById(new Topic().setId(topicId).setIsTop(0));
                break;
            case "精华":
                baseMapper.updateById(new Topic().setId(topicId).setIsEssence(1));
                break;
            case "取消精华":
                baseMapper.updateById(new Topic().setId(topicId).setIsEssence(0));
                break;
            default:
                break;
        }

        return true;
    }
}
