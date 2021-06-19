package com.mazouri.ketangpai.service.impl;

import com.mazouri.ketangpai.entity.NoticeUser;
import com.mazouri.ketangpai.entity.SysUser;
import com.mazouri.ketangpai.mapper.NoticeUserMapper;
import com.mazouri.ketangpai.service.NoticeUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mazouri.ketangpai.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mazouri
 * @since 2021-06-19
 */
@Service
public class NoticeUserServiceImpl extends ServiceImpl<NoticeUserMapper, NoticeUser> implements NoticeUserService {

}
