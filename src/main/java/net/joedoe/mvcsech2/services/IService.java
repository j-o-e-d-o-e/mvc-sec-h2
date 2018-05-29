package net.joedoe.mvcsech2.services;

import java.util.List;

public interface IService<T> {
    List<T> listAll();

    T getById(Integer id);

    T getByName(String name);

    void saveOrUpdate(T t);
}
