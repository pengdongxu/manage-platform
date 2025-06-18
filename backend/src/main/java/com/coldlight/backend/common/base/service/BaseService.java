package com.coldlight.backend.common.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.coldlight.backend.common.base.request.PageRequest;

import java.util.Optional;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/18 17:09
 * @description 定义通用服务接口
 */
public interface BaseService<T, Q extends PageRequest> {

    Optional<T> getByIdOpt(Long id);

    boolean save(T entity);

    boolean updateById(T entity);

    boolean deleteById(Long id);

    IPage<T> page(Q query, int pageNum, int pageSize);
}
