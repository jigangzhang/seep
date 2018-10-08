package com.god.seep.dao;

public interface BaseDao<T> {
    Long save(T t);

    void delete(Long id);

    void update(T t);

    T load(Long id);
}
