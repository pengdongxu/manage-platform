package com.coldlight.backend.usermanagement.manage;

import com.coldlight.backend.usermanagement.model.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/20 16:44
 * @description 核心逻辑
 */
@Component
@RequiredArgsConstructor
public class SysUserManage {

    private final SysUserManage sysUserManage;

    public SysUser getById(Long id) {
        return sysUserManage.getById(id);
    }

    public boolean save(SysUser user) {
        return sysUserManage.save(user);
    }

    public boolean updateById(SysUser user) {
        return sysUserManage.updateById(user);
    }

    public boolean deleteById(Long id) {
        return sysUserManage.deleteById(id);
    }
}
