package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mazouri
 * @create 2021-06-19 15:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommentVO extends Comment {
    private String username;
    private String avatar;
}
