package com.god.seep.service;

public interface BaseService<T> {
    Long save(T t);

    void delete(Long id);

    void update(T t);

    T load(Long id);
}
