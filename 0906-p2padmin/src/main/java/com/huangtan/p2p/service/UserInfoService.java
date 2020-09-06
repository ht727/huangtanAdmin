package com.huangtan.p2p.service;

import com.huangtan.p2p.model.UserInfo;

public interface UserInfoService {
    UserInfo queryUserInfoByUserNameAndPassword(String username, String password);
}
