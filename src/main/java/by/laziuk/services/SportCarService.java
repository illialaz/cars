package by.laziuk.services;

import by.laziuk.cars.impl.SportCar;
import by.laziuk.cars.impl.Vehicle;
import by.laziuk.dao.CsvDao;
import by.laziuk.dao.Dao;
import by.laziuk.dao.JsonDao;
import by.laziuk.dao.XMLDao;

import java.util.List;
import java.util.Map;

public class SportCarService implements ICarService {
    final private Dao<SportCar> dao;
    final private String fileName;

    public SportCarService(String type) {
        switch (type){
            case "CSV": {
                dao = new XMLDao<>(SportCar.class);
                fileName = "sportcars.xml";
                break;
            }
            case "XML": {
                dao = new CsvDao<>(SportCar.class);
                fileName = "sportcars.csv";
                break;
            }
            default:
                dao = new JsonDao<>(SportCar.class);
                fileName = "sportcars.json";
                break;
        }
    }

    @Override
    public void add(Map<String, String> car) {
        List<SportCar> cars = get("none");

        String id = car.get("id");

        boolean unique = true;
        for(SportCar vehicle : cars) {
            if (vehicle.getId().equals(id)) {
                unique = false;
                break;
            }
        };

        if(!unique) return;

        cars.add(new SportCar(car));
        dao.write(cars, fileName);
    }

    @Override
    public void delete(String id) {
        List<SportCar> cars = get("none");

        boolean isDeleted = false;
        for(SportCar vehicle : cars) {
            if (vehicle.getId().equals(id)) {
                isDeleted = true;
                cars.remove(vehicle);
                break;
            }
        };

        if(isDeleted) dao.write(cars, fileName);
    }

    @Override
    public void add(Vehicle car) {

    }

    @Override
    public void patch(String id, Map<String, String> newCar) {
        List<SportCar> cars = get("none");

        boolean isChanged = false;
        for(SportCar vehicle : cars) {
            if (vehicle.getId().equals(id)) {
                isChanged = true;
                cars.remove(vehicle);
                break;
            }
        };

        if(!isChanged) return;

        cars.add(new SportCar(newCar));
        dao.write(cars, fileName);
    }

    @Override
    public List<SportCar> get(String sortParam) {
        List<SportCar> res = dao.read(fileName);

        if(sortParam.equals("none")) return res;

        for(Vehicle.SortType a : Vehicle.SortType.values()) {
            if (a.toString().equals(sortParam)) {
                res.sort(Vehicle.getComparator(a));
                break;
            }
        }
        return res;
    }

    @Override
    public Vehicle getOne(String id) {
        return null;
    }
}
