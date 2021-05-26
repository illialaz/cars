package by.laziuk.dao;

import by.laziuk.cars.impl.Identifiable;

import java.util.List;

public abstract class Dao<T extends Identifiable> implements IDao<T> {
    protected Class<T> type;
    public Dao(Class<T> type) { this.type = type; }
    @Override
    public void update(T obj) {
        List<T> list = read();
        boolean isChanged = false;
        for(T data : list) {
            if(data.getId().equals(obj.getId())) {
                list.remove(data);
                list.add(obj);
                isChanged = true;
                break;
            }
        }
        if(isChanged) write(list);
    }

    @Override
    public void delete(String id) {
        List<T> list = read();
        for(T data : list) {
            if(data.getId().equals(id)) {
                list.remove(data);
                write(list);
                break;
            }
        }

    }

    @Override
    public void create(T obj) {
        List<T> data = read();
        for(T elem : data) {
            if(elem.getId().equals(obj.getId())) {
                update(obj);
                return;
            }
        }
        data.add(obj);
        write(data);
    }

    @Override
    public T readOne(String id) {
        List<T> list = read();
        for(T data : list) {
            if(data.getId().equals(id)) return data;
        }
        return null;
    }
}
