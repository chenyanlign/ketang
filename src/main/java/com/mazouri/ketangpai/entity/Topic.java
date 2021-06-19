package com.mazouri.ketangpai.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Topic对象", description="")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "话题名称")
    private String talkTitle;

    @ApiModelProperty(value = "讨论内容")
    private String content;

    @ApiModelProperty(value = "创建者的id")
    private String userId;

    @ApiModelProperty(value = "创建者的id")
    private String username;

    @ApiModelProperty(value = "是否置顶 0 否 1 置顶")
    @TableField(fill = FieldFill.INSERT)
    private Integer isTop;

    @ApiModelProperty(value = "是否为精华 0 否 1 是")
    @TableField(fill = FieldFill.INSERT)
    private Integer isEssence;

    @ApiModelProperty(value = "是否已删除 0 没有 1 删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "点赞数")
    @TableField(fill = FieldFill.INSERT)
    private Integer likeNum;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
