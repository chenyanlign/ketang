package com.mazouri.ketangpai.service;

import com.mazouri.ketangpai.entity.AttendUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mazouri.ketangpai.entity.vo.AttendUserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-20
 */
public interface AttendUserService extends IService<AttendUser> {

    List<AttendUserVO> getAllUserByAttendId(String attendId);
}
