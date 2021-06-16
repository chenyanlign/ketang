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
@ApiModel(value="Course对象", description="")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "加课码")
    private String code;

    @ApiModelProperty(value = "学年(2020-2021)")
    private String semester;

    @ApiModelProperty(value = "创建老师")
    private String createTeacherId;

    @ApiModelProperty(value = "学期（1或2）")
    private String term;

    @ApiModelProperty(value = "课堂")
    private String classname;

    @ApiModelProperty(value = "近期作业")
    private String recentWork;

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
