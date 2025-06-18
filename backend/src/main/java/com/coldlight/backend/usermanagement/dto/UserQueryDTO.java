package com.coldlight.backend.usermanagement.dto;

import com.coldlight.backend.common.base.request.PageRequest;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/18 17:29
 * @description TODO
 */
@Data
public class UserQueryDTO extends PageRequest {
    @Serial
    private static final long serialVersionUID = -6092967181369917144L;
}
