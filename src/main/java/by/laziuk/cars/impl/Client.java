package by.laziuk.cars.impl;

import java.util.ArrayList;
import java.util.List;

public class Client {
    List<Vehicle> cars;

    public Client() {
        cars = new ArrayList<>();
    }

    public Client(List<Vehicle> cars) {
        this.cars = cars;
    }

    public void setCars(List<Vehicle> cars) {
        this.cars = cars;
    }

    public List<Vehicle> getCars() {
        return cars;
    }
}
