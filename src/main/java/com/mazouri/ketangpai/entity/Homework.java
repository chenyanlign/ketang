package com.mazouri.ketangpai.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Homework对象", description="")
public class Homework implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "作业id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "作业名称")
    private String title;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "作业描述")
    private String description;

    @ApiModelProperty(value = "截止时间")
    private Date deadline;

    @ApiModelProperty(value = "满分值")
    private Integer fullScore;

    @ApiModelProperty(value = "已批改数")
    private Integer checkNum;

    @ApiModelProperty(value = "未批改数")
    private Integer noCheckNum;

    @ApiModelProperty(value = "未提交数")
    private Integer noSubmitNum;

    @ApiModelProperty(value = "作业类型")
    private String type;

    @ApiModelProperty(value = "作业附件路径")
    private String fileName;

    @ApiModelProperty(value = "0 正常 1 删除")
    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;


}
