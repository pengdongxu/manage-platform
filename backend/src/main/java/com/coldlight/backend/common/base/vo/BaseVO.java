package com.coldlight.backend.common.base.vo;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/20 13:51
 * @description 基础VO
 */
public abstract class BaseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3080246098883475360L;

    private Long id;
}
