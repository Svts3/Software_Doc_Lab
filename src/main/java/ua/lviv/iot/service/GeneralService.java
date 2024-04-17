package ua.lviv.iot.service;

import java.util.Collection;

public interface GeneralService<T, ID> {

    T save(T entity);

    Collection<T> findAll();

    T findById(ID id);

    T update(ID id, T entity);

    T deleteById(ID id);
    

}
