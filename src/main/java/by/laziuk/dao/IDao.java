package by.laziuk.dao;

import by.laziuk.cars.impl.Identifiable;

import java.util.List;

public interface IDao<T extends Identifiable> {
    List<T> read();
    T readOne(String id);
    void create(T obj);
    void update(T obj);
    void write(List<T> list);
    void delete(String id);
}
