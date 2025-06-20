package com.coldlight.backend.usermanagement.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.coldlight.backend.common.utils.DateUtils;
import com.coldlight.backend.usermanagement.dto.SysUserDTO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/13 17:21
 * @description 系统用户
 */
@Getter
@Builder
@TableName("SYS_USER")
public class SysUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 5207730509424127663L;

    @TableId
    private Long id;
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
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    //创建人 ID
    private String createUser;
    //修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
    //修改人 ID
    private String updateUser;


    public static SysUser from(SysUserDTO user) {
        return SysUser.builder()
                .account(user.getAccount())
                .password(PasswordEncoder.encode(user.getPassword()))
                .status(user.getStatus())
                .mobile(user.getMobile())
                .remark(user.getRemark())
                .createTime(DateUtils.getCurrentTimestamp())
                .build();
    }
}
