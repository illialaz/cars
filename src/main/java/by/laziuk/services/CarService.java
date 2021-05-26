package by.laziuk.services;

import by.laziuk.cars.impl.Statistics;
import by.laziuk.cars.impl.Vehicle;
import by.laziuk.dao.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public class CarService<T extends Vehicle> implements ICarService<T> {
  
    final protected IDao<T> dao;
    final public ReentrantLock lock = new ReentrantLock();
    final Class<T> clazz;

    public CarService(Class<T> clazz, IDao<T> dao) {
        this.clazz = clazz;
        this.dao = dao;
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }

    @Override
    public void add(T car) {
        if(car.getId().length() != 36) car.setId("");
        dao.create(car);
    }

    @Override
    public void patch(T car) {
        dao.update(car);
    }

    @Override
    public Optional<T> getOne(String id) {
        return get(null).stream().filter((item) -> item.getId().equals(id)).findFirst();
    }

    @Override
    public List<T> get(@Nullable String sortParam) {
        lock.lock();
        List<T> res = dao.read();
        lock.unlock();
        if(sortParam == null) {
            return res;
        }
        if(sortParam.equals(Statistics.AGEUNDER18) ||
                sortParam.equals(Statistics.AGEBETWEEN18AND30) ||
                sortParam.equals(Statistics.AGEBETWEEN30AND50) ||
                sortParam.equals(Statistics.AGEAFTER50)) {
            res.sort(Statistics.getComparator(sortParam));
            return res;
        }
        for(Vehicle.SortType a : Vehicle.SortType.values()) {
            if (a.toString().equals(sortParam)) {
                res.sort(Vehicle.getComparator(a));
                break;
            }
        }
        return res;
    }
}
    
