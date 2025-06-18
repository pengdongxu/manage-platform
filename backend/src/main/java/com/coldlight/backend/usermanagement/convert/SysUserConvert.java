package com.coldlight.backend.usermanagement.convert;

import com.coldlight.backend.usermanagement.dto.SysUserDTO;
import com.coldlight.backend.usermanagement.model.SysUser;
import com.coldlight.backend.usermanagement.vo.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/18 16:52
 * @description 转换器
 */
@Mapper(componentModel = "spring")  // 交给Spring管理
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);


    SysUser dtoToEntity(SysUserDTO dto);

    SysUserVO entityToVO(SysUser user);

    SysUserDTO entityToDTO(SysUser user);


}
