package com.huangtan.p2p.service.impl;

import com.huangtan.p2p.mapper.PermissionInfoMapper;
import com.huangtan.p2p.mapper.UserInfoMapper;
import com.huangtan.p2p.model.PermissionInfo;
import com.huangtan.p2p.model.UserInfo;
import com.huangtan.p2p.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    PermissionInfoMapper permissionInfoMapper;
    @Override
    public UserInfo queryUserInfoByUserNameAndPassword(String username, String password) {
        UserInfo userInfo = userInfoMapper.selectUserInfoByUserNameAndPassword(username,password);
        if(null==userInfo){
            return  null;
        }
        //根据用户的Id查询用户角色以及用户具有的权限信息
        List<PermissionInfo> permissionInfoList =  permissionInfoMapper.selectPermissionInfoByUserId(userInfo.getId(),1);
        if(null != permissionInfoList && permissionInfoList.size()>0) {
            userInfo.setPermissionInfoList(permissionInfoList);
            return  userInfo;
        }
        return null;
    }
}
