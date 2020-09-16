package com.huangtan.p2p.service.impl;

import com.huangtan.p2p.mapper.RoleInfoMapper;
import com.huangtan.p2p.model.RoleInfo;
import com.huangtan.p2p.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    RoleInfoMapper roleInfoMapper;

    @Override
    public List<RoleInfo> queryAllRoleInfo() {
        return roleInfoMapper.selectAllRoleInfo();
    }
}
