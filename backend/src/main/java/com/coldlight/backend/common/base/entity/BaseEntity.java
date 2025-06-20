package com.coldlight.backend.common.base.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/20 13:49
 * @description 基础实体类
 */
@Data
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1361363659323468698L;

    private Long id;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;
}
