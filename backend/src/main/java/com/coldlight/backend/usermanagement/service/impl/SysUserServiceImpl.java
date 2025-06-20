package com.coldlight.backend.usermanagement.service.impl;

import com.coldlight.backend.usermanagement.convert.SysUserConvert;
import com.coldlight.backend.usermanagement.dto.SysUserDTO;
import com.coldlight.backend.usermanagement.manage.SysUserManage;
import com.coldlight.backend.usermanagement.model.SysUser;
import com.coldlight.backend.usermanagement.service.SysUserService;
import com.coldlight.backend.usermanagement.vo.SysUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/20 15:02
 * @description 系统用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserManage userManage;
    private final SysUserConvert userConvert;


    @Override
    public SysUserVO getById(Long id) {
        SysUser sysUser = userManage.getById(id);
        return userConvert.entityToVO(sysUser);
    }

    @Override
    public boolean save(SysUserDTO user) {
        SysUser sysUser = SysUser.from(user);
        return userManage.save(sysUser);
    }


    @Override
    public boolean updateById(SysUser user) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
