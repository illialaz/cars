package by.laziuk.dao;

import by.laziuk.cars.impl.Identifiable;
import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.util.List;

public class NoSQLDao<T extends Identifiable> extends Dao<T> {
    private final Datastore datastore;
    public NoSQLDao(Class<T> type, String name) {
        super(type);
        Morphia morphia = new Morphia();
        morphia.map(type);
        datastore = morphia.createDatastore(new MongoClient(), name);
        datastore.ensureIndexes();
    }

    @Override
    public List<T> read() {
        return datastore.createQuery(type).find().toList();
    }

    @Override
    public void write(List<T> list) {
        for(T item : list) datastore.save(item);
    }

    @Override
    public void update(T obj) {
        datastore.merge(obj);
    }

    @Override
    public void delete(String id) {
        datastore.delete(datastore.createQuery(type).field("id").equal(id).first());
    }

    @Override
    public void create(T obj) {
        datastore.save(obj);
    }

    @Override
    public T readOne(String id) {
        return datastore.createQuery(type).field("id").equal(id).first();
    }
}
