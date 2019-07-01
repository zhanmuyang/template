package com.bytebpm.base.service;

/**
 * Created by DDTang on 16/5/6.
 */

import org.springframework.transaction.annotation.Transactional;

import com.bytebpm.mybatis.CrudMapper;

import java.io.Serializable;
import java.util.List;

public abstract class CrudServiceImpl <T, ID extends Serializable, R extends CrudMapper<T, ID>> implements
        CrudService<T, ID> {
    protected R repository;

    public abstract void setRepository(R repository) ;

    @Override
    @Transactional(readOnly = false)
    public T createSelective(T resource) {
        repository.insertSelective(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public T create(T resource) {
        repository.insert(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public T update(T resource) {
        repository.updateByPrimaryKey(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public T updateSelective(T resource) {
        repository.updateByPrimaryKeySelective(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(ID id) {
        repository.deleteByPrimaryKey(id);
    }

    @Override
    public T findOne(ID id) {
        return repository.selectByPrimaryKey(id);
    }

    @Override
    public List<T> findAll() {
        return repository.selectAll();
    }

}
