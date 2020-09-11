package com.huangtan.p2p.service.impl;

import com.huangtan.p2p.mapper.PermissionInfoMapper;
import com.huangtan.p2p.mapper.UserInfoMapper;
import com.huangtan.p2p.model.PermissionInfo;
import com.huangtan.p2p.model.UserInfo;
import com.huangtan.p2p.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            //获取用户所拥有的的URL数据
            List<String> urlList = permissionInfoMapper.selectURLPermissionByUserId(userInfo.getId());
            //遍历集合使用map保存，因为HashMap是散列表数据结构，查询效率快
            Map<String,String> urlMap = new HashMap<>();
            for(String url :urlList){
                urlMap.put(url,"1");//key可以保证无序不重复，value这里使用不上
            }
            //将所有的url地址封装到UserInfo对象中，并保存到session中
            userInfo.setUrlMap(urlMap);
            return  userInfo;
        }
        return null;
    }
}
