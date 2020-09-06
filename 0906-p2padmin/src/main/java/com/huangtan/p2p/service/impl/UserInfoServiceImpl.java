package com.huangtan.p2p.service.impl;

import com.huangtan.p2p.mapper.UserInfoMapper;
import com.huangtan.p2p.model.UserInfo;
import com.huangtan.p2p.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo queryUserInfoByUserNameAndPassword(String username, String password) {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserNameAndPassword(username,password);
        return userInfo;
    }
}
