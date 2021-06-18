package com.mazouri.ketangpai.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author mazouri
 * @create 2021-06-18 17:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HomeworkVO {
    /**
     * 学号
     */
    private String account;

    private String userId;

    /**
     * 成绩
     */
    private String grade;
    /**
     * 满分
     */
    private String fullScore;
    /**
     * 学生姓名
     */
    private String username;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 提交作业的id
     */
    private String finishWorkId;

    /**
     * 提交日期
     */
    private Date createTime;
}
