package by.laziuk.dao;

import java.util.List;

public interface IDao<T> {
    List<T> read(String fileName);
    void write(List<T> data, String fileName);
}
