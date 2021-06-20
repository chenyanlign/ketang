package com.mazouri.ketangpai.entity.vo;

import com.mazouri.ketangpai.entity.Attend;
import com.mazouri.ketangpai.entity.AttendUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author mazouri
 * @create 2021-06-20 18:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AttendUserVO", description="")
public class AttendUserVO extends AttendUser {
    private String username;
    private String account;
    private String status;
    private String userId;
}
