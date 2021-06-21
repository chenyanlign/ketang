package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.Homework;
import com.mazouri.ketangpai.entity.SubmitHomework;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mazouri
 * @create 2021-06-21 9:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SubmitHomeworkVO", description="")
public class SubmitHomeworkVO extends Homework {
    private String username;
    private String account;
    private String filePath;
    private String grade;
    private Integer total;
}
