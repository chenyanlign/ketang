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
 * @since 2021-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SubmitHomework对象", description="")
public class SubmitHomework implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String homeworkId;

    @ApiModelProperty(value = "学生id")
    private String userId;

    @ApiModelProperty(value = "分数")
    private String grade;

    @ApiModelProperty(value = "作业路径")
    private String filePath;

    @ApiModelProperty(value = "留言")
    private String leaveWord;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "是否被批改，0 没有 1 被批改")
    @TableField(fill = FieldFill.INSERT)
    private Integer checked;

    @ApiModelProperty(value = "0 正常 1 删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
