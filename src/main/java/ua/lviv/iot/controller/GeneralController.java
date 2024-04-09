package ua.lviv.iot.controller;

public interface GeneralController<T, ID> {

    String findAll();

    String findById(ID id);

    String update(T entity, ID id);

    String deleteById(ID id);
}
