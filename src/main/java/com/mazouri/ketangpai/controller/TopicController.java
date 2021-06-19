package com.mazouri.ketangpai.controller;


import com.mazouri.ketangpai.common.result.R;
import com.mazouri.ketangpai.entity.Topic;
import com.mazouri.ketangpai.entity.vo.TopicVO;
import com.mazouri.ketangpai.service.TopicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
@RestController
@RequestMapping("/ketangpai/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @ApiOperation(value = "获取某课程下的所有的话题")
    @GetMapping("/getAllTopicByCourseId/{courseId}")
    public R getAllTopicByCourseId(@PathVariable String courseId) {
        List<TopicVO> topics = topicService.getTopicList(courseId);
        return R.ok().data("topics", topics);
    }

    @ApiOperation(value = "获取某课程下的所有的话题")
    @GetMapping("/getTopicById/{id}")
    public R getTopicById(@PathVariable String id) {
        TopicVO topic = topicService.getTopicById(id);
        return R.ok().data("topic", topic);
    }

    @ApiOperation(value = "创建话题")
    @PostMapping("/addTopic")
    public R addTopic(@RequestBody Topic topic) {
        return topicService.save(topic) ? R.ok() : R.error();
    }

    @ApiOperation(value = "删除话题")
    @PostMapping("/removeTopic/{topicId}")
    public R removeTopic(@PathVariable String topicId) {
        return topicService.removeById(topicId) ? R.ok() : R.error();
    }

    @ApiOperation(value = "更新话题")
    @PostMapping("/updateTopOrEssence")
    public R updateTopic(@RequestParam String topicId, @RequestParam String type) {
        return topicService.updateTopOrEssence(topicId, type) ? R.ok() : R.error();
    }

}

