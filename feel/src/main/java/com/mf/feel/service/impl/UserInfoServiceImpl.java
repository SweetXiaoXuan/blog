package com.mf.feel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mf.feel.mapper.UserInfoMapper;
import com.mf.feel.model.UserInfo;
import com.mf.feel.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author xurunfei
 * @since 2019-02-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
