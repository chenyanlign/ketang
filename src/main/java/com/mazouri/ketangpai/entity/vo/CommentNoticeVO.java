package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.CommentNotice;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mazouri
 * @create 2021-06-19 23:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CommentNotice对象", description="")
public class CommentNoticeVO extends CommentNotice {
    private String username;
}
