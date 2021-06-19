package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.Topic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mazouri
 * @create 2021-06-19 12:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopicVO extends Topic {
    private String username;
    private Integer commentNum;
}
