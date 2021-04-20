package by.laziuk.dao;

public abstract class Dao<T> implements IDao<T> {
    protected Class<T> type;
    public Dao(Class<T> type) { this.type = type; }
}
