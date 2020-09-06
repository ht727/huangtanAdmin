package com.huangtan.p2p.model;

import java.util.ArrayList;
import java.util.List;

public class PermissionInfo {
    private Integer id;

    private String name;

    private String type;

    private String url;

    private String code;

    private Integer parentid;

    private Double sort;

    private Integer available;

    //权限一对多的关系，表示一个权限可能有多个子权限
    private List<PermissionInfo> permissionInfoList = new ArrayList<>();

    public List<PermissionInfo> getPermissionInfoList() {
        return permissionInfoList;
    }

    public void setPermissionInfoList(List<PermissionInfo> permissionInfoList) {
        this.permissionInfoList = permissionInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}