package com.nikolenko.homeworks.dao;

import java.util.List;

public interface Repository<T> {
    T getById(Long Id);
    T getById(String Id);
    void delete (Long Id);
    void delete (String Id);
    boolean exists(Long Id);
    boolean exists(String Id);
    List<T> getAll();
    Long count();
    T insert(T rec);

}
