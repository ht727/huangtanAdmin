package com.huangtan.p2p.mapper;

import com.huangtan.p2p.model.PermissionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PermissionInfo record);

    int insertSelective(PermissionInfo record);

    PermissionInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionInfo record);

    int updateByPrimaryKey(PermissionInfo record);

    List<PermissionInfo> selectPermissionInfoByUserId(Integer userId,Integer parentid);
}