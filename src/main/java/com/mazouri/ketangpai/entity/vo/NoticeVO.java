package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.Notice;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mazouri
 * @create 2021-06-19 20:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="NoticeVO对象", description="")
public class NoticeVO extends Notice {
    private String username;
    private Integer commentNum;
    private Integer readNum;
}
