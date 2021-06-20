package com.mazouri.ketangpai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mazouri.ketangpai.entity.Attend;
import com.mazouri.ketangpai.entity.AttendUser;
import com.mazouri.ketangpai.entity.vo.AttendUserVO;
import com.mazouri.ketangpai.mapper.AttendUserMapper;
import com.mazouri.ketangpai.service.AttendService;
import com.mazouri.ketangpai.service.AttendUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-20
 */
@Service
public class AttendUserServiceImpl extends ServiceImpl<AttendUserMapper, AttendUser> implements AttendUserService {
    @Autowired
    private AttendService attendService;

    @Override
    public List<AttendUserVO> getAllUserByAttendId(String attendId) {
        Attend attend = attendService.getById(attendId);
       List<AttendUserVO> attendUsers =  baseMapper.getAllUserByCourseId(attend.getCourseId());
       attendUsers.forEach(attendUser -> {
           AttendUser au = baseMapper.selectOne(new QueryWrapper<AttendUser>()
                   .eq("user_id", attendUser.getUserId()).eq("attend_id", attendId));
           if (au!=null){
               attendUser.setStatus("正常").setCreateTime(au.getCreateTime()).setPosition(au.getPosition())
                       .setAttendIp(au.getAttendIp()).setAttendId(au.getAttendId());
           }else {
               attendUser.setStatus("旷课");
           }
       });
        return attendUsers;
    }
}
