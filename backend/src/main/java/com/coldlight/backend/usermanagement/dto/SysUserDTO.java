package com.coldlight.backend.usermanagement.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/18 16:50
 * @description 新增用户DTO
 */
@Data
public class SysUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8506863074722310254L;

    //账号
    private String account;
    //密码
    private String password;
    //状态
    private Integer status;
    //手机
    private String mobile;
    // 备注
    private String remark;
}
