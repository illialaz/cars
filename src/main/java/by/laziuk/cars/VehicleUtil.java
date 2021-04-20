package by.laziuk.cars;

import by.laziuk.cars.impl.Jeep;
import by.laziuk.cars.impl.Minivan;
import by.laziuk.cars.impl.SportCar;
import by.laziuk.cars.impl.Vehicle;

import java.util.ArrayList;
import java.util.List;

public abstract class VehicleUtil {
    public static List<SportCar> getSportCars(List<Vehicle> cars) {
        List<SportCar> sportCars = new ArrayList<>();
        cars.forEach(car -> {
            if(car instanceof SportCar) sportCars.add((SportCar) car);
        });
        return sportCars;
    }

    public static List<Jeep> getJeeps(List<Vehicle> cars) {
        List<Jeep> jeeps = new ArrayList<>();
        cars.forEach(car -> {
            if(car instanceof Jeep) jeeps.add((Jeep) car);
        });
        return jeeps;
    }

    public static List<Minivan> getMinivans(List<Vehicle> cars) {
        List<Minivan> minivans = new ArrayList<>();
        cars.forEach(car -> {
            if(car instanceof Minivan) minivans.add((Minivan)car);
        });
        return minivans;
    }

    public static List<Vehicle> link(List<? extends Vehicle>... list) {
        List<Vehicle> res = new ArrayList<>();
        for(List<? extends Vehicle> elem : list) res.addAll(elem);
        return res;
    }
}
