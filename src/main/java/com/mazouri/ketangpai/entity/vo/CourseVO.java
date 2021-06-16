package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mazouri
 * @create 2021-06-14 23:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseVO extends Course {
    private String recentWorkTitle;
    /**
     * 这个班级的人数
     */
    private Integer courseNum;

    private String createTeacher;

    private String teacherAvatar;

    private Integer archived;
}
