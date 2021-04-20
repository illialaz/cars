package by.laziuk.services;

import by.laziuk.cars.impl.Jeep;
import by.laziuk.cars.impl.Vehicle;
import by.laziuk.dao.CsvDao;
import by.laziuk.dao.Dao;
import by.laziuk.dao.JsonDao;
import by.laziuk.dao.XMLDao;

import java.util.List;
import java.util.Map;

public class JeepService implements ICarService {
    final private Dao<Jeep> dao;
    final private String fileName;

    public JeepService(String type) {
        switch (type){
            case "CSV": {
                dao = new XMLDao<>(Jeep.class);
                fileName = "jeeps.xml";
                break;
            }
            case "XML": {
                dao = new CsvDao<>(Jeep.class);
                fileName = "jeeps.csv";
                break;
            }
            default:
                dao = new JsonDao<>(Jeep.class);
                fileName = "jeeps.json";
                break;
        }
    }

    @Override
    public void add(Map<String, String> car) {
        List<Jeep> cars = get("none");

        String id = car.get("id");

        boolean unique = true;
        for(Jeep vehicle : cars) {
            if (vehicle.getId().equals(id)) {
                unique = false;
                break;
            }
        };

        if(!unique) return;

        cars.add(new Jeep(car));
        dao.write(cars, fileName);
    }

    @Override
    public void delete(String id) {
        List<Jeep> cars = get("none");

        boolean isDeleted = false;
        for(Jeep vehicle : cars) {
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
        List<Jeep> cars = get("none");

        boolean isChanged = false;
        for(Jeep vehicle : cars) {
            if (vehicle.getId().equals(id)) {
                isChanged = true;
                cars.remove(vehicle);
                break;
            }
        };

        if(!isChanged) return;

        cars.add(new Jeep(newCar));
        dao.write(cars, fileName);
    }

    @Override
    public List<Jeep> get(String sortParam) {
        List<Jeep> res = dao.read(fileName);

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
