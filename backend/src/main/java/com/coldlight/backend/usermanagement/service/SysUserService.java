package com.coldlight.backend.usermanagement.service;

import com.coldlight.backend.usermanagement.dto.SysUserDTO;
import com.coldlight.backend.usermanagement.model.SysUser;
import com.coldlight.backend.usermanagement.vo.SysUserVO;


/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/20 14:45
 * @description 系统用户服务
 */
public interface SysUserService {

    SysUserVO getById(Long id);

    boolean save(SysUserDTO user);

    boolean updateById(SysUser user);

    boolean deleteById(Long id);
}
