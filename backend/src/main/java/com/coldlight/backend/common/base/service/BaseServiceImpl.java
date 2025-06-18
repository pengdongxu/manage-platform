package com.coldlight.backend.common.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.coldlight.backend.common.base.request.PageRequest;

import java.util.Optional;

/**
 * @author pengpdx
 * @version 1.0
 * @date 2025/6/18 17:22
 * @description 抽象类实现
 */
public abstract class BaseServiceImpl<T, Q extends PageRequest> implements BaseService<T, Q> {

    // 具体业务实现类必须提供对应的Mapper，比如 UserMapper
    protected abstract BaseMapper<T> getBaseMapper();

    // 构建查询条件，子类可重写定制
    protected QueryWrapper<T> buildQueryWrapper(Q query) {
        return new QueryWrapper<>();
    }

    @Override
    public Optional<T> getByIdOpt(Long id) {
        return Optional.ofNullable(getBaseMapper().selectById(id));
    }

    @Override
    public boolean save(T entity) {
        return getBaseMapper().insert(entity) > 0;
    }

    @Override
    public boolean updateById(T entity) {
        return getBaseMapper().updateById(entity) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return getBaseMapper().deleteById(id) > 0;
    }

    @Override
    public IPage<T> page(Q query, int pageNum, int pageSize) {
        // 构建查询条件，子类可重写此方法实现自定义查询逻辑
        QueryWrapper<T> wrapper = buildQueryWrapper(query);

        // 构造分页对象
        Page<T> page = new Page<>(query.getPageNum(), query.getPageSize());

        // 添加排序
        if (query.getSortField() != null && !query.getSortField().isEmpty()) {
            boolean isAsc = "asc".equalsIgnoreCase(query.getSortOrder());
            page.addOrder(isAsc ? OrderItem.asc(query.getSortField()) : OrderItem.desc(query.getSortField()));
        }

        return getBaseMapper().selectPage(page, wrapper);
    }
}
