package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.Attend;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author mazouri
 * @create 2021-06-20 23:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AttendVO", description="")
public class AttendVO extends Attend {
    private String userId;
    private Date attendTime;
    private String status;
}
