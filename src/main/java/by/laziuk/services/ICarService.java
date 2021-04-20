package by.laziuk.services;

import by.laziuk.cars.impl.Vehicle;

import java.util.List;
import java.util.Map;

public interface ICarService {
    void add(Map<String, String> car);
    void delete(String id);
    void add(Vehicle car);
    void patch(String id, Map<String, String> newCar);
    List<? extends Vehicle> get(String sortParam);
    Vehicle getOne(String id);
}
