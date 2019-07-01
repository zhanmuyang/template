package com.bytebpm.base.service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {

    T createSelective(T resource);

    T create(T resource);

    T update(T resource);

    T updateSelective(T resource);

    void delete(ID id);

    T findOne(ID id);

    List<T> findAll();
}
