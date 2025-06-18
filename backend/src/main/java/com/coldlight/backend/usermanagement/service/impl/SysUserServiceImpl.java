package com.coldlight.backend.usermanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coldlight.backend.common.base.service.BaseServiceImpl;
import com.coldlight.backend.usermanagement.dto.UserQueryDTO;
import com.coldlight.backend.usermanagement.mapper.UserMapper;
import com.coldlight.backend.usermanagement.model.SysUser;
import com.coldlight.backend.usermanagement.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/18 17:31
 * @description TODO
 */
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, UserQueryDTO> implements SysUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseMapper<SysUser> getBaseMapper() {
        return userMapper;
    }

    @Override
    protected QueryWrapper<SysUser> buildQueryWrapper(UserQuery query) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (query == null) return wrapper;

        if (query.getUsername() != null && !query.getUsername().isEmpty()) {
            wrapper.like("username", query.getUsername());
        }

        if (query.getStatus() != null) {
            wrapper.eq("status", query.getStatus());
        }

        if (query.getStartDate() != null) {
            wrapper.ge("create_time", query.getStartDate());
        }

        if (query.getEndDate() != null) {
            wrapper.le("create_time", query.getEndDate());
        }

        return wrapper;
    }