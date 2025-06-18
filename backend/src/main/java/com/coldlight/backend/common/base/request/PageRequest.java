package com.coldlight.backend.common.base.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/18 17:13
 * @description
 */
@Data
public class PageRequest implements Serializable {


    // 当前页码，默认1
    private int pageNum = 1;
    // 每页条数，默认10
    private int pageSize = 10;
    // 排序字段
    private String sortField;
    // 排序顺序，asc 或 desc(默认)
    private String sortOrder;

}
